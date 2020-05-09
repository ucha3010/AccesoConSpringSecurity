// Elimina los diacríticos de un texto excepto si es una "ñ" (ES6)
//
function normalizado(texto) {
    return texto
           .normalize('NFD')
           .replace(/([^n\u0300-\u036f]|n(?!\u0303(?![\u0300-\u036f])))[\u0300-\u036f]+/gi,"$1")
           .normalize();
}

function ddmmyyyy(date) {
  var mm = date.getMonth() + 1; // getMonth() is zero-based
  var dd = date.getDate();
  
  return [(dd>9 ? '' : '0') + dd + '-' + (mm>9 ? '' : '0') + mm + '-' + date.getFullYear()
         ].join('');    
}