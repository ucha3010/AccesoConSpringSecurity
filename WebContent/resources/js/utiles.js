// Elimina los diacríticos de un texto excepto si es una "ñ" (ES6)
//
function normalizado(texto) {
    return texto
           .normalize('NFD')
           .replace(/([^n\u0300-\u036f]|n(?!\u0303(?![\u0300-\u036f])))[\u0300-\u036f]+/gi,"$1")
           .normalize();
}