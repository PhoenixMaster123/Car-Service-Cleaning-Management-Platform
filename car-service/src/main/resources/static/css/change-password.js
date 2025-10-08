document.addEventListener('DOMContentLoaded', () => {

    // --- DOM Element Selection ---
    const fullPageLoader = document.getElementById('full-page-loader');
    const mainContent = document.getElementById('main-content');
    const passwordForm = document.getElementById('password-form');
    const allInputs = passwordForm.querySelectorAll('input');
    const allButtons = document.querySelectorAll('button');
    const currentPasswordInput = document.getElementById('currentPassword');
    const newPasswordInput = document.getElementById('newPassword');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const submitPasswordBtn = document.getElementById('submit-password-btn');
    const toggle2faBtn = document.getElementById('toggle-2fa-btn');
    const twoFactorInfo = document.getElementById('two-factor-info');
    const strengthIndicator = document.getElementById('strength-indicator');
    const strengthBar = document.getElementById('strength-bar');
    const strengthLabel = document.getElementById('strength-label');
    const statusMessageEl = document.getElementById('status-message');
    const statusTextEl = document.getElementById('status-text');
    const statusIconEl = document.getElementById('status-icon');

    // --- State Management ---
    let isPasswordChanging = false;
    let isTwoFactorToggling = false;
    let isTwoFactorEnabled = false;
    let statusTimer;

    // --- Theme Management ---
    document.body.classList.add('dark-theme');

    // --- Utility Functions ---
    const toggleButtonLoading = (button, isLoading, originalText) => {
        const btnText = button.querySelector('.btn-text');
        if (isLoading) {
            button.disabled = true;
            // Use data-feather for the loader icon
            btnText.innerHTML = `<i data-feather="loader" class="loader-icon-small"></i> ${button.dataset.loadingText || 'Loading...'}`;
            feather.replace(); // Use feather.replace()
        } else {
            button.disabled = false;
            btnText.textContent = originalText;
        }
    };

    const setFormDisabledState = (disabled) => {
        allInputs.forEach(input => input.disabled = disabled);
        allButtons.forEach(button => {
            if (button !== submitPasswordBtn && button !== toggle2faBtn) {
                button.disabled = disabled;
            }
        });
    };

    const showStatusMessage = (message, type) => {
        clearTimeout(statusTimer);
        statusTextEl.textContent = message;
        statusMessageEl.className = 'status-message';
        statusMessageEl.classList.add(type);

        if (type === 'success') {
            statusIconEl.innerHTML = `<i data-feather="check-circle" class="icon"></i>`;
        } else {
            statusIconEl.innerHTML = `<i data-feather="x-circle" class="icon"></i>`;
        }
        feather.replace(); // Use feather.replace()

        statusMessageEl.style.display = 'flex';
        statusTimer = setTimeout(() => {
            statusMessageEl.style.display = 'none';
        }, 4000);
    };

    // --- Core Logic Functions ---
    const updatePasswordStrength = () => {
        const password = newPasswordInput.value;
        if (password.length === 0) {
            strengthIndicator.style.display = 'none';
            return;
        }

        strengthIndicator.style.display = 'block';
        let strength = 0;
        if (password.length > 7) strength++;
        if (password.match(/[a-z]/)) strength++;
        if (password.match(/[A-Z]/)) strength++;
        if (password.match(/[0-9]/)) strength++;
        if (password.match(/[^A-Za-z0-9]/)) strength++;
        const cappedStrength = Math.min(strength, 4);
        const labels = ['Very Weak', 'Weak', 'Fair', 'Good', 'Strong'];
        strengthBar.style.width = `${(strength / 5) * 100}%`;
        strengthLabel.textContent = labels[cappedStrength];
        strengthBar.className = 'strength-bar';
        strengthLabel.className = '';
        if (strength > 0) {
            strengthBar.classList.add(`strength-${cappedStrength}`);
            strengthLabel.classList.add(`strength-${cappedStrength}`);
        }
    };

    const update2FA_UI = () => {
        if (isTwoFactorEnabled) {
            twoFactorInfo.textContent = "2FA is currently enabled, adding an extra layer of security.";
            toggle2faBtn.querySelector('.btn-text').textContent = "Disable 2FA";
            toggle2faBtn.className = 'btn btn-disable';
            toggle2faBtn.dataset.loadingText = "Disabling...";
        } else {
            twoFactorInfo.textContent = "Enable 2FA to better protect your account from unauthorized access.";
            toggle2faBtn.querySelector('.btn-text').textContent = "Enable 2FA";
            toggle2faBtn.className = 'btn btn-enable';
            toggle2faBtn.dataset.loadingText = "Enabling...";
        }
    };

    // --- Event Handlers ---
    const handlePasswordSubmit = async (event) => {
        event.preventDefault();
        if (isPasswordChanging || isTwoFactorToggling) return;

        if (newPasswordInput.value !== confirmPasswordInput.value) {
            showStatusMessage("New passwords do not match.", 'error');
            return;
        }
        if (newPasswordInput.value.length < 8) {
            showStatusMessage("New password must be at least 8 characters long.", 'error');
            return;
        }

        isPasswordChanging = true;
        setFormDisabledState(true);
        toggleButtonLoading(submitPasswordBtn, true, 'Change Password');

        try {
            await new Promise((resolve, reject) => {
                setTimeout(() => {
                    if (currentPasswordInput.value === 'correctCurrentPassword123!') {
                        resolve();
                    } else {
                        reject(new Error("Incorrect current password."));
                    }
                }, 1500);
            });
            showStatusMessage("Password changed successfully!", 'success');
            passwordForm.reset();
            updatePasswordStrength();
        } catch (err) {
            showStatusMessage(err.message, 'error');
        } finally {
            isPasswordChanging = false;
            setFormDisabledState(false);
            toggleButtonLoading(submitPasswordBtn, false, 'Change Password');
        }
    };

    const handleToggleTwoFactor = async () => {
        if (isPasswordChanging || isTwoFactorToggling) return;

        isTwoFactorToggling = true;
        setFormDisabledState(true);
        const originalText = isTwoFactorEnabled ? 'Disable 2FA' : 'Enable 2FA';
        toggleButtonLoading(toggle2faBtn, true, originalText);

        try {
            await new Promise(resolve => setTimeout(resolve, 1000));
            isTwoFactorEnabled = !isTwoFactorEnabled;
            update2FA_UI();
            showStatusMessage(`Two-Factor Authentication ${isTwoFactorEnabled ? 'enabled' : 'disabled'} successfully.`, 'success');
        } catch (err) {
            showStatusMessage(`Failed to update 2FA status.`, 'error');
        } finally {
            isTwoFactorToggling = false;
            setFormDisabledState(false);
            const newOriginalText = isTwoFactorEnabled ? 'Disable 2FA' : 'Enable 2FA';
            toggleButtonLoading(toggle2faBtn, false, newOriginalText);
        }
    };

    const togglePasswordVisibility = (event) => {
        const button = event.currentTarget;
        const targetInputId = button.dataset.target;
        const targetInput = document.getElementById(targetInputId);

        if (targetInput.type === 'password') {
            targetInput.type = 'text';
            button.innerHTML = '<i data-feather="eye-off" class="icon"></i>';
        } else {
            targetInput.type = 'password';
            button.innerHTML = '<i data-feather="eye" class="icon"></i>';
        }
        feather.replace(); // Use feather.replace()
    };

    // --- Initial Fetch & Event Listeners Setup ---
    const fetchInitialSettings = async () => {
        try {
            await new Promise(resolve => setTimeout(() => {
                isTwoFactorEnabled = true;
                resolve();
            }, 1000));
            update2FA_UI();
        } catch (error) {
            showStatusMessage("Failed to load security settings.", 'error');
        } finally {
            fullPageLoader.style.display = 'none';
            mainContent.style.visibility = 'visible';
        }
    };

    passwordForm.addEventListener('submit', handlePasswordSubmit);
    newPasswordInput.addEventListener('input', updatePasswordStrength);
    toggle2faBtn.addEventListener('click', handleToggleTwoFactor);
    document.querySelectorAll('.password-toggle').forEach(button => {
        button.addEventListener('click', togglePasswordVisibility);
    });

    submitPasswordBtn.dataset.loadingText = "Changing...";
    fetchInitialSettings();

    // Create initial icons after the page and scripts are ready
    feather.replace(); // Use feather.replace()

});