const btnToggle = document.querySelector('.du-toggle-btn');

btnToggle.addEventListener('click', function() {
	document.getElementById('du-sidebar').classList.toggle('active');
});