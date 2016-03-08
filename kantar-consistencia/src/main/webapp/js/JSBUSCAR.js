
function doSearchAll() {
	var tableReg = document.getElementById('polizas');
	for (var i = 1; i < tableReg.rows.length; i++) {
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		for (var j = 0; j < cellsOfRow.length; j++) {
			tableReg.rows[i].style.display = '';
		}
	}
	doSearch();
	doSearchEstado();
}
function doSearch() {
	var searchText = document.getElementById('busqueda').value.toLowerCase();
	doBusqueda(searchText);
}
function doSearchEstado() {
	var searchText = document.getElementById('estado').value.toLowerCase();
	doBusqueda(searchText);
}
function doBusqueda(searchText){
	var tableReg = document.getElementById('polizas');
	var cellsOfRow = "";
	var found = false;
	var compareWith = "";
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 1; i < tableReg.rows.length; i++) {
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++) {
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0
					|| (compareWith.indexOf(searchText) > -1)) {
				found = true;
			}
		}
		if (found) {
//			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la // fila de
			// la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}