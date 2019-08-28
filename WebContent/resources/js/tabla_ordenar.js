function sortTable(col) {
	var table, rows, switching, i, x, y, shouldSwitch, fecha1, fecha2;
	table = document.getElementById("tablaOrdenar");
	switching = true;
	/*
	 * Make a loop that will continue until no switching has been done:
	 */
	while (switching) {
		// start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/*
		 * Loop through all table rows (except the first, which contains table
		 * headers):
		 */
		for (i = 1; i < (rows.length - 1); i++) {
			// start by saying there should be no switching:
			shouldSwitch = false;
			/*
			 * Get the two elements you want to compare, one from current row
			 * and one from the next:
			 */
			x = rows[i].getElementsByTagName("TD")[col];
			y = rows[i + 1].getElementsByTagName("TD")[col];
			if(x.innerHTML.substr(2,1) === '/' && x.innerHTML.substr(5,1) === '/'){
				fecha1 = x.innerHTML.substr(6,4) + x.innerHTML.substr(3,2) + x.innerHTML.substr(0,2);
				fecha2 = y.innerHTML.substr(6,4) + y.innerHTML.substr(3,2) + y.innerHTML.substr(0,2);
				if (fecha1 > fecha2) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}				
			} else {
				// check if the two rows should switch place:
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/*
			 * If a switch has been marked, make the switch and mark that a
			 * switch has been done:
			 */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
		}
	}
}