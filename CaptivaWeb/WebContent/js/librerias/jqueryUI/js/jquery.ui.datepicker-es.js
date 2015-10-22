/* Inicialización en español para la extensión 'UI date picker' para jQuery. */
/* Traducido por Vester (xvester@gmail.com). */
(function( factory ) {
if ( typeof define === "function" && define.amd ) {

// AMD. Register as an anonymous module.
define([ "../datepicker" ], factory );
} else {

// Browser globals
factory( jQuery.datepicker );
}
}(function( datepicker ) {

datepicker.regional['es'] = {
closeText: 'Cerrar',
prevText: '&#x3C;Ant',
nextText: 'Sig&#x3E;',
currentText: 'Hoy',
monthNames: ['enero','febrero','marzo','abril','mayo','junio',
'julio','agosto','septiembre','octubre','noviembre','diciembre'],
monthNamesShort: ['ene','feb','mar','abr','may','jun',
'jul','ago','sep','oct','nov','dic'],
dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
weekHeader: 'Sm',
dateFormat: 'dd/mm/yy',
firstDay: 1,
isRTL: false,
showMonthAfterYear: false,
yearSuffix: ''};
datepicker.setDefaults(datepicker.regional['es']);

return datepicker.regional['es'];

}));