// Função para abrir o modal de adição de veículo
function openAddVehicleModal() {
    document.getElementById('addVehicleModal').style.display = 'block';
}

// Função para fechar o modal
function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Função para exibir campos adicionais com base no tipo de veículo
function showAdditionalFields(action, vehicleType) {
    const additionalFieldsContainer = document.getElementById(
        action === 'add' ? 'addAdditionalFields' : 'editAdditionalFields'
    );

    // Limpa o conteúdo do container antes de adicionar novos campos
    additionalFieldsContainer.innerHTML = '';

    if (vehicleType === 'carro') {
        // Adiciona o campo para "Carro"
        additionalFieldsContainer.innerHTML = `
            <input type="number" id="addVehicleDoors" placeholder="Número de Portas" required>
        `;
    } else if (vehicleType === 'moto') {
        // Adiciona o campo para "Moto"
        additionalFieldsContainer.innerHTML = `
            <input type="number" id="addVehicleCylinderCapacity" placeholder="Cilindrada (CC)" required>
        `;
    }
}

// Função para carregar a lista de veículos
async function loadVehicles() {
    try {
        console.log('Tentando carregar a lista de veículos...');

        // Realiza a requisição GET
        const response = await fetch('http://localhost:8080/vehicles');

        // Verifica se a resposta foi bem-sucedida
        if (!response.ok) {
            throw new Error(`Erro ao carregar veículos: ${response.statusText}`);
        }

        // Converte a resposta para JSON
        const vehiclesData = await response.json();
        console.log('Dados dos veículos retornados:', vehiclesData); // Exibe os dados no console

        // Verifica se as propriedades cars e motorcycles existem e são arrays válidos
        const cars = Array.isArray(vehiclesData.cars) ? vehiclesData.cars : [];
        const motorcycles = Array.isArray(vehiclesData.motorcycles) ? vehiclesData.motorcycles : [];

        // Combina os dois arrays em um único array de veículos
        const vehicles = [...cars, ...motorcycles];

        if (vehicles.length > 0) {
            displayVehicles(vehicles); // Exibe os veículos
        } else {
            console.warn('Nenhum veículo encontrado');
            document.getElementById('vehiclesList').innerHTML = '<tr><td colspan="8">Não há veículos cadastrados.</td></tr>';
        }

    } catch (error) {
        // Captura qualquer erro de rede ou de validação da resposta
        console.error('Erro na requisição GET:', error); // Loga o erro
        alert('Erro ao conectar-se ao servidor. Tente novamente mais tarde.');
    }
}

// Função para exibir os veículos na interface
function displayVehicles(vehicles) {
    const vehiclesList = document.getElementById('vehiclesList');
    vehiclesList.innerHTML = ''; // Limpa a lista antes de adicionar os itens

    if (!vehicles || vehicles.length === 0) {
        console.warn('Nenhum veículo encontrado');
        vehiclesList.innerHTML = '<tr><td colspan="8">Não há veículos cadastrados.</td></tr>';
        return;
    }

    vehicles.forEach(vehicle => {
        console.log('Exibindo veículo:', vehicle); // Logando cada veículo

        // Cria a linha da tabela
        const row = document.createElement('tr');

        // Logando as propriedades para verificar o que está sendo retornado
        console.log('Propriedades do veículo:', vehicle);

        // Verifica se o veículo é um carro ou moto com base nas propriedades
        let type = 'Desconhecido';

        // Verificando se a propriedade 'doors' existe para carros
        if (vehicle.hasOwnProperty('doors')) {
            type = 'Carro';  // Carros possuem a propriedade 'doors'
        }
        // Verificando se a propriedade 'cylinder' existe para motos
        else if (vehicle.hasOwnProperty('cylinder')) {
            type = 'Moto';   // Motos possuem a propriedade 'cylinder'
        }

        console.log(`Veículo identificado como: ${type}`); // Logando o tipo identificado

        // Preenche as células com os dados do veículo
        row.innerHTML = `
            <td>${vehicle.manufacturer}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.year}</td>
            <td>${vehicle.price.toFixed(2)} R$</td>
            <td>${type}</td>  <!-- Aqui adicionamos o tipo -->
            <td>${vehicle.fuel}</td>
            <td>${vehicle.doors || vehicle.cylinder || 'N/A'}</td> <!-- Alteração para 'cylinder' -->
            <td>
                <button onclick="editVehicle(${vehicle.id})">Editar</button>
                <button onclick="deleteVehicle(${vehicle.id})">Excluir</button>
            </td>
        `;

        // Adiciona a linha na tabela
        vehiclesList.appendChild(row);
    });
}

// Função para abrir o modal de edição com os dados do veículo
function openEditVehicleModal(vehicleId, vehicleType) {
    // Preenche os campos do formulário com os dados do veículo
    document.getElementById('editVehicleId').value = vehicleId;

    // Dependendo do tipo do veículo, preenche os outros campos
    // Exemplo: Suponha que você tenha uma função `getVehicleById` que retorna os dados do veículo
    fetch(`http://localhost:8080/vehicles/${vehicleType === 'car' ? 'getCar' : 'getMotorcycle'}/${vehicleId}`)
        .then(response => response.json())
        .then(vehicle => {
            document.getElementById('editVehicleManufacturer').value = vehicle.manufacturer;
            document.getElementById('editVehicleModel').value = vehicle.model;
            document.getElementById('editVehicleYear').value = vehicle.year;
            document.getElementById('editVehiclePrice').value = vehicle.price;
            document.getElementById('editVehicleType').value = vehicleType;

            // Verifica se há campos adicionais para o tipo de veículo
            showAdditionalFields('edit', vehicleType);

            // Abre o modal
            document.getElementById('editVehicleModal').style.display = 'block';
        })
        .catch(error => {
            console.error('Erro ao carregar os dados do veículo:', error);
        });
}

// Função para salvar as alterações do veículo
async function saveEditedVehicle(event) {
    event.preventDefault(); // Previne o envio normal do formulário

    const vehicleId = document.getElementById('editVehicleId').value;
    const manufacturer = document.getElementById('editVehicleManufacturer').value;
    const model = document.getElementById('editVehicleModel').value;
    const year = document.getElementById('editVehicleYear').value;
    const price = document.getElementById('editVehiclePrice').value;
    const vehicleType = document.getElementById('editVehicleType').value;

    // Estrutura de dados a ser enviada
    const vehicleData = {
        manufacturer,
        model,
        year,
        price,
        fuel: document.getElementById('editVehicleFuel').value,
    };

    // Adiciona campos adicionais dependendo do tipo de veículo
    if (vehicleType === 'carro') {
        // Adicione os campos específicos para carro
        vehicleData.type = 'car';
    } else if (vehicleType === 'moto') {
        // Adicione os campos específicos para moto
        vehicleData.type = 'motorcycle';
    }

    const url = vehicleType === 'carro'
        ? `http://localhost:8080/vehicles/updateCar/${vehicleId}`
        : `http://localhost:8080/vehicles/updateMotorcycle/${vehicleId}`;

    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(vehicleData),
        });

        if (!response.ok) {
            throw new Error('Erro ao editar o veículo.');
        }

        const result = await response.json();
        console.log('Veículo editado com sucesso:', result);
        alert("Veículo editado com sucesso!");

        // Fecha o modal após a edição
        closeModal('editVehicleModal');
    } catch (error) {
        console.error('Erro ao editar veículo:', error);
        alert("Ocorreu um erro ao editar o veículo.");
    }
}

// Função para fechar o modal
function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}



// Função para excluir veículo
async function deleteVehicle(button) {
    const vehicleId = button.getAttribute('data-vehicle-id');

    if (!vehicleId) {
        console.error("ID do veículo não fornecido.");
        alert("Erro: ID do veículo não fornecido.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/vehicles/delete/${vehicleId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir o veículo.');
        }

        const result = await response.json();
        console.log('Veículo excluído com sucesso:', result);
        alert("Veículo excluído com sucesso!");

        // Aqui você pode atualizar a UI, por exemplo, removendo o veículo da lista
        // Remova o item da lista ou atualize a interface conforme necessário
    } catch (error) {
        console.error('Erro ao excluir veículo:', error);
        alert("Ocorreu um erro ao excluir o veículo.");
    }
}


// Função para salvar um novo veículo
async function saveNewVehicle(event) {
    event.preventDefault();

    // Obtém os valores do formulário
    const manufacturer = document.getElementById('addVehicleManufacturer').value;
    const model = document.getElementById('addVehicleModel').value;
    const year = document.getElementById('addVehicleYear').value;
    const price = document.getElementById('addVehiclePrice').value;
    const vehicleType = document.getElementById('addVehicleType').value;
    const fuel = document.getElementById('addVehicleFuel').value;

    let additionalData = {};

    if (vehicleType === 'carro') {
        const doors = document.getElementById('addVehicleDoors').value;
        additionalData = { doors };
    } else if (vehicleType === 'moto') {
        const cylinder = document.getElementById('addVehicleCylinderCapacity').value; // Alterado para cylinder
        additionalData = { cylinder }; // Alterado para cylinder
    }

    // Define a URL da API com base no tipo de veículo
    const url =
        vehicleType === 'carro'
            ? 'http://localhost:8080/vehicles/createCar'
            : 'http://localhost:8080/vehicles/createMotorcycle';

    // Dados a serem enviados
    const payload = {
        manufacturer,
        model,
        year: parseInt(year, 10),
        price: parseFloat(price),
        fuel,
        ...additionalData,
    };

    try {
        // Envia a requisição para a API
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
        });

        if (response.ok) {
            alert('Veículo adicionado com sucesso!');
            closeModal('addVehicleModal');
            // Limpar formulário e atualizar lista de veículos (opcional)
            document.getElementById('addVehicleForm').reset();
            loadVehicles(); // Recarrega a lista de veículos
        } else {
            const errorData = await response.json();
            console.error('Erro ao adicionar veículo:', errorData);
            alert('Erro ao adicionar o veículo. Verifique os dados e tente novamente.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao conectar-se ao servidor. Tente novamente mais tarde.');
    }
}

// Executa uma configuração inicial para exibir "Número de Portas" como padrão
document.addEventListener('DOMContentLoaded', () => {
    showAdditionalFields('add', 'carro');
    loadVehicles(); // Carrega a lista de veículos ao carregar a página
});
