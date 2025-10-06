document.addEventListener('DOMContentLoaded', function () {
    // --- Sample Data for Vehicles ---
    // In a real application, this data would come from a server
    const vehicleData = {
        vehicle1: {
            make: 'Volkswagen',
            model: 'Golf VIII',
            year: 2023,
            licensePlate: 'WÜ-AS 2025',
            vin: 'WVWZZZAUZNP123456'
        },
        vehicle2: {
            make: 'Audi',
            model: 'A4',
            year: 2022,
            licensePlate: 'WÜ-X 123',
            vin: 'WAUZZZ8K9CN000111'
        },
        vehicle3: {
            make: 'BMW',
            model: 'i3',
            year: 2024,
            licensePlate: 'WÜ-E 456',
            vin: 'WBY1Z2C52KE789101'
        }
    };

    // --- Get DOM Elements ---
    const vehicleSelector = document.getElementById('vehicleSelector');
    const makeInput = document.getElementById('vehicleMake');
    const modelInput = document.getElementById('vehicleModel');
    const yearInput = document.getElementById('vehicleYear');
    const licenseInput = document.getElementById('licensePlate');
    const vinInput = document.getElementById('vin');

    // --- Function to Update Form Fields ---
    function updateVehicleForm(selectedVehicleId) {
        const selectedVehicle = vehicleData[selectedVehicleId];

        if (selectedVehicle) {
            makeInput.value = selectedVehicle.make;
            modelInput.value = selectedVehicle.model;
            yearInput.value = selectedVehicle.year;
            licenseInput.value = selectedVehicle.licensePlate;
            vinInput.value = selectedVehicle.vin;
        }
    }

    // --- Event Listener for Dropdown Change ---
    vehicleSelector.addEventListener('change', function(event) {
        const selectedId = event.target.value;
        updateVehicleForm(selectedId);
    });

    // --- Initial Load ---
    // Load the data for the initially selected vehicle when the page loads
    updateVehicleForm(vehicleSelector.value);
});