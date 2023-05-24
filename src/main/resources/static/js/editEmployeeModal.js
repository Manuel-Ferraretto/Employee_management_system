const editEmployeeButton = document.getElementById('edit-employee-button');
const editEmployeeModal = document.getElementById('edit-employee-modal');
const editEmployeeForm = document.getElementById('edit-employee-form');
const editSuccessMessage = document.getElementById('editSuccessMessage');
const cancel = document.getElementById('close');


cancel.addEventListener('click', () => {
    editEmployeeModal.style.display = 'none';
});

editEmployeeForm.addEventListener('submit', (event) => {
  event.preventDefault();

   var formData = {
      idEmployee: $('#idEmployee').val(),
      name: $('#firstName').val(),
      lastName: $('#lastName').val(),
      email: $('#editEmail').val(),
      dateOfBirth: $('#dateOfBirth').val(),
      salary: $('#editSalary').val()
    };
    $.ajax({
         type: 'POST',
         contentType: 'application/json',
         url: '/employee/saveChanges/' + formData.idEmployee,
         data: JSON.stringify(formData),
         dataType: 'json',
         success: function(response) {
           window.location.href = response.redirectUrl;
           alert("Employee updated successfully!");
            },
         error: function(request, status) {
            console.log(request.responseText);
            console.log(status);
            }
    });

  newEmployeeModal.style.display = 'none';
  newEmployeeForm.reset();
});

function editEmployee(ID){
    $.ajax({
        url: '/employee/getEmployee/' + ID,
        type: 'GET',
        success: function(data){
            // Populate the modal with the data
            $('#idEmployee').val(data.idEmployee);
            $('#firstName').val(data.name);
            $('#lastName').val(data.lastName);
            $('#editEmail').val(data.email);
            $('#dateOfBirth').val(data.dateOfBirth);
            $('#editSalary').val(data.salary);

            // Display the modal
             editEmployeeModal.style.display = 'block';

        },
        error: function(request, status, error) {
                            console.log(request.responseText);
                            console.log(status);
                            console.log(error);
                            }
    });
}


