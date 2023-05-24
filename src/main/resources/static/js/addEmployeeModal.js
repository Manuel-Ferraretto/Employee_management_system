const newEmployeeButton = document.getElementById('new-employee-button');
const newEmployeeModal = document.getElementById('new-employee-modal');
const newEmployeeForm = document.getElementById('new-employee-form');
const successMessage = document.getElementById('successMessage')
const close = document.querySelector('.close');

newEmployeeButton.addEventListener('click', () => {
  newEmployeeModal.style.display = 'block';
});

close.addEventListener('click', () => {
  newEmployeeModal.style.display = 'none';
});

/*
window.addEventListener('click', (event) => {
  if (event.target == newEmployeeModal) {
    newEmployeeModal.style.display = 'none';
  }
}); */

newEmployeeForm.addEventListener('submit', (event) => {
  event.preventDefault();

  // Send data to server using AJAX or fetch() here
   var formData = {
      name: $('#fname').val(),
      lastName: $('#lname').val(),
      email: $('#email').val(),
      dateOfBirth: $('#dob').val(),
      salary: $('#salary').val()
    };

    $.ajax({
         type: 'POST',
         contentType: 'application/json',
         url: '/employee/addNewEmployee',
         data: JSON.stringify(formData),
         dataType: 'json',
         success: function(response) {
           window.location.href = response.redirectUrl;
           alert("Employee created successfully!");
            },
         error: function(request, status, error) {
            console.log(request.responseText);
            console.log(status);
            console.log(error);
            }
    });

  newEmployeeModal.style.display = 'none';
  newEmployeeForm.reset();
});
