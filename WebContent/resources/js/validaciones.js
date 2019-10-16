function restablecer(){
	var errorSpan = document.getElementsByName('errorSpan');
	for (var i = 0; i < errorSpan.length; i++) {
		errorSpan[i].style.borderColor="#ced4da";
		errorSpan[i].innerHTML='';
	}
}