// Função para abrir o modal de adição de veículo
function openAddVehicleModal() {
    document.getElementById('addVehicleModal').style.display = 'block';
}

// Função para fechar o modal
function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Função para exibir campos adicionais com base no tipo de veículo
function showAdditionalFields(action, vehicleType, vehicle = null) {
    const additionalFieldsContainer = document.getElementById(
        action === 'add' ? 'addAdditionalFields' : 'editAdditionalFields'
    );

    // Limpa o conteúdo do container antes de adicionar novos campos
    additionalFieldsContainer.innerHTML = '';

    if (vehicleType === 'carro') {
        // Adiciona o campo para "Carro"
        additionalFieldsContainer.innerHTML = `
            <input type="number" id="${action === 'add' ? 'add' : 'edit'}VehicleDoors" placeholder="Número de Portas" required ${action === 'edit' && vehicle ? `value="${vehicle.additionalFields.numDoors}"` : ''}>`;
    } else if (vehicleType === 'moto') {
        // Adiciona o campo para "Moto"
        additionalFieldsContainer.innerHTML = `
            <input type="number" id="${action === 'add' ? 'add' : 'edit'}VehicleCylinderCapacity" placeholder="Cilindrada (CC)" required ${action === 'edit' && vehicle ? `value="${vehicle.additionalFields.engineCapacity}"` : ''}>`;
    }
}

// Função para carregar a lista de veículos
let currentPage = 1;
const itemsPerPage = 8;

async function loadVehicles() {
    try {
        console.log('Tentando carregar a lista de veículos...');
        const response = await fetch('http://localhost:8080/vehicles');
        if (!response.ok) {
            throw new Error(`Erro ao carregar veículos: ${response.statusText}`);
        }
        const vehiclesData = await response.json();
        console.log('Dados dos veículos retornados:', vehiclesData);

        const cars = Array.isArray(vehiclesData.cars) ? vehiclesData.cars : [];
        const motorcycles = Array.isArray(vehiclesData.motorcycles) ? vehiclesData.motorcycles : [];
        const vehicles = [...cars, ...motorcycles];

        if (vehicles.length > 0) {
            displayVehicles(vehicles);
        } else {
            console.warn('Nenhum veículo encontrado');
            document.getElementById('vehiclesList').innerHTML = '<tr><td colspan="8">Não há veículos cadastrados.</td></tr>';
        }
    } catch (error) {
        console.error('Erro na requisição GET:', error);
        alert('Erro ao conectar-se ao servidor. Tente novamente mais tarde.');
    }
}

//Função de busca
function searchVehiclesOnClick() {
    const input = document.getElementById('search');
    const filter = input.value.toLowerCase();
    const table = document.getElementById('vehiclesList');
    const rows = table.getElementsByTagName('tr');


    for (let i = 0; i < rows.length; i++) {
        const row = rows[i];
        const cells = row.getElementsByTagName('td');
        let match = false;


        for (let j = 0; j < cells.length; j++) {
            if (cells[j]) {
                const cellText = cells[j].textContent || cells[j].innerText;
                const attributes = cells[j].dataset;


                if (
                    cellText.toLowerCase().includes(filter) ||
                    Object.values(attributes).some(attrValue => attrValue.toLowerCase().includes(filter))
                ) {
                    match = true;
                    break;
                }
            }
        }


        row.style.display = match ? '' : 'none';
    }
}

// Função para exibir os veículos na interface
function displayVehicles(vehicles) {
    const vehiclesList = document.getElementById('vehiclesList');
    const paginationContainer = document.getElementById('pagination');

    vehiclesList.innerHTML = '';
    paginationContainer.innerHTML = '';

    const totalPages = Math.ceil(vehicles.length / itemsPerPage);

    if (currentPage > totalPages) {
        currentPage = totalPages;
    }

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const vehiclesToDisplay = vehicles.slice(startIndex, endIndex);

    vehiclesToDisplay.forEach(vehicle => {
        const row = document.createElement('tr');
        let type = 'Desconhecido';
        if (vehicle.hasOwnProperty('doors')) {
            type = 'Carro';
        } else if (vehicle.hasOwnProperty('cylinder')) {
            type = 'Moto';
        }

        row.innerHTML = `
            <td>${vehicle.manufacturer}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.year}</td>
            <td>${vehicle.price.toFixed(2)} R$</td>
            <td>${type}</td>
            <td>${vehicle.fuel}</td>
            <td>${vehicle.doors || vehicle.cylinder || 'N/A'}</td>
            <td class="btn-action">
               <button class="btn-edit" onclick="openEditVehicleModal('${vehicle.idVehicle}', '${type}')">Editar</button>
               <button class="btn-delete" onclick="deleteVehicle('${vehicle.idVehicle}', '${type}')">Excluir</button>
            </td>
        `;
        vehiclesList.appendChild(row);
    });

    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.innerText = i;
        pageLink.href = "#";
        pageLink.classList.add('page-link');

        if (i === currentPage) {
            pageLink.classList.add('active');
        }

        if (i === 1) {
            pageLink.classList.add('first-page');
        }

        pageLink.onclick = (e) => {
            e.preventDefault();
            currentPage = i;
            displayVehicles(vehicles);
        };

        paginationContainer.appendChild(pageLink);
    }
}

// Função para abrir o modal de edição
function openEditVehicleModal(idVehicle, vehicleType) {
    // Preenche o campo de ID do veículo
    const editVehicleId = document.getElementById('editVehicleId');
    if (editVehicleId) {
        editVehicleId.value = idVehicle;
    }

    // Dependendo do tipo do veículo, preenche os outros campos
    fetch(`http://localhost:8080/vehicles/${vehicleType === 'Carro' ? 'car' : 'motorcycle'}/${idVehicle}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao buscar veículo: ${response.statusText}`);
            }
            return response.json();
        })
        .then(vehicle => {
            const manufacturerField = document.getElementById('editVehicleManufacturer');
            if (manufacturerField) {
                manufacturerField.value = vehicle.manufacturer || '';
            }

            const modelField = document.getElementById('editVehicleModel');
            if (modelField) {
                modelField.value = vehicle.model || '';
            }

            const yearField = document.getElementById('editVehicleYear');
            if (yearField) {
                yearField.value = vehicle.year || '';
            }

            const priceField = document.getElementById('editVehiclePrice');
            if (priceField) {
                priceField.value = vehicle.price ? vehicle.price.toFixed(2) : '';
            }

            const fuelField = document.getElementById('editVehicleFuel');
            if (fuelField) {
                fuelField.value = vehicle.fuel || '';
            }

            const typeField = document.getElementById('editVehicleType');
            if (typeField) {
                typeField.value = vehicleType;
            }

            // Exibe campos adicionais, dependendo do tipo do veículo
            showAdditionalFields('edit', vehicleType, vehicle);

            // Exibe o modal de edição
            const editVehicleModal = document.getElementById('editVehicleModal');
            if (editVehicleModal) {
                editVehicleModal.style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Erro ao carregar os dados do veículo:', error);
            alert('Erro ao carregar os dados do veículo: ' + error.message);
        });
}

///////////Função para salvar as alterações de um veículo//////////////////
async function updateVehicle(event) {
    event.preventDefault();

    // Verifica se os elementos existem antes de tentar acessar o valor
    const manufacturerElement = document.getElementById('editVehicleManufacturer');
    const modelElement = document.getElementById('editVehicleModel');
    const yearElement = document.getElementById('editVehicleYear');
    const priceElement = document.getElementById('editVehiclePrice');
    const vehicleTypeElement = document.getElementById('editVehicleType');
    const fuelElement = document.getElementById('editVehicleFuel');
    const doorsElement = document.getElementById('editVehicleDoors');
    const cylinderElement = document.getElementById('editVehicleCylinderCapacity');

    // Verifica se todos os elementos do formulário estão presentes
    if (!manufacturerElement || !modelElement || !yearElement || !priceElement || !vehicleTypeElement || !fuelElement) {
        console.error('Erro: um ou mais campos obrigatórios não foram encontrados.');
        alert('Erro ao acessar os campos do formulário. Tente novamente mais tarde.');
        return;
    }

    // Obtém os valores dos campos
    const manufacturer = manufacturerElement.value;
    const model = modelElement.value;
    const year = yearElement.value;
    const price = priceElement.value;
    const vehicleType = vehicleTypeElement.value;
    const fuel = fuelElement.value;

    let additionalData = {};

// Adiciona dados adicionais de acordo com o tipo de veículo
    if (vehicleType === 'carro' && doorsElement) {
        const doors = doorsElement.value;
        additionalData = { ...additionalData, doors };  // Adiciona 'doors' ao objeto existente
    } else if (vehicleType === 'moto' && cylinderElement) {
        const cylinder = cylinderElement.value;
        additionalData = { ...additionalData, cylinder };  // Adiciona 'cylinder' ao objeto existente
    }

    // Obtém o ID do veículo sendo editado
    const vehicleId = document.getElementById('editVehicleId').value;

    // Define a URL da API com base no tipo de veículo
    const url =
        vehicleType === 'carro'
            ? `http://localhost:8080/vehicles/updateCar/${vehicleId}`
            : `http://localhost:8080/vehicles/updateMotorcycle/${vehicleId}`;

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
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
        });

        if (response.ok) {
            alert('Veículo atualizado com sucesso!');
            closeModal('editVehicleModal');
            // Limpar formulário e atualizar lista de veículos (opcional)
            document.getElementById('editVehicleForm').reset();
            loadVehicles(); // Recarrega a lista de veículos
        } else {
            const errorData = await response.json();
            console.error('Erro ao atualizar veículo:', errorData);
            alert('Erro ao atualizar o veículo. Verifique os dados e tente novamente.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro ao conectar-se ao servidor. Tente novamente mais tarde.');
    }
}

// Configuração inicial
document.addEventListener('DOMContentLoaded', () => {
    loadVehicles(); // Carrega a lista de veículos ao carregar a página
});

/////////////////Função para salvar um novo veículo//////////////////////////
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
        const cylinder = document.getElementById('addVehicleCylinderCapacity').value;
        additionalData = { cylinder };
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

///////////////Função para excluir um veiculo///////////////
async function deleteVehicle(vehicleId, vehicleType) {
    // Confirmação antes de excluir o veículo
    const confirmation = confirm("Tem certeza de que deseja excluir este veículo?");

    if (confirmation) {
        // Define a URL da API com base no tipo de veículo
        const url =
            vehicleType === 'Carro'
                ? `http://localhost:8080/vehicles/deleteCar/${vehicleId}`
                : `http://localhost:8080/vehicles/deleteMotorcycle/${vehicleId}`;

        // Adiciona um log para verificar a URL
        console.log("URL de exclusão:", url);

        try {
            // Envia a requisição para a API
            const response = await fetch(url, {
                method: 'DELETE',
            });

            if (response.ok) {
                alert('Veículo excluído com sucesso!');
                // Atualiza a lista de veículos na interface (opcional)
                loadVehicles(); // Recarrega a lista de veículos
            } else {
                const errorData = await response.json();
                console.error('Erro ao excluir veículo:', errorData);
                alert('Erro ao excluir o veículo. Tente novamente.');
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Erro ao conectar-se ao servidor. Tente novamente mais tarde.');
        }
    }
}
