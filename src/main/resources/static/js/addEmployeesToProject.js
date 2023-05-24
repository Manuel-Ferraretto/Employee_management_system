const addToProject = document.getElementById('add-employees-button');
const editEmployeeModal = document.getElementById('edit-employee-modal');
const editEmployeeForm = document.getElementById('edit-employee-form');
const cancel = document.getElementById('close');


cancel.addEventListener('click', () => {
    editEmployeeModal.style.display = 'none';
});

