document.addEventListener('DOMContentLoaded', function() {
    const resetButton = document.getElementById("resetButton");
    const newPassword = document.querySelector('input[name="newPassword"]');
    const confirmPassword = document.querySelector('input[name="confirmPassword"]');
    const errorMessage = document.querySelector('.error-message');

    function validatePasswords() {
        const newPasswordValue = newPassword.value;
        const confirmPasswordValue = confirmPassword.value;

        if (newPasswordValue && confirmPasswordValue) {
            if (newPasswordValue === confirmPasswordValue) {
                resetButton.disabled = false;
                resetButton.classList.remove('disabled-button');
                errorMessage.style.display = 'none';
            } else {
                resetButton.disabled = true;
                resetButton.classList.add('disabled-button');
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'Passwords do not match!';
            }
        } else {
            resetButton.disabled = true;
            resetButton.classList.add('disabled-button');
            errorMessage.style.display = 'none';
        }
    }

    newPassword.addEventListener('input', validatePasswords);
    confirmPassword.addEventListener('input', validatePasswords);
});