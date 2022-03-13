
/**
 * parses a field from a json blob,
 * creates a column with the field information.
 * adds the field to the row.
 *
 * @param row Table row object that will have a column appended to it.
 * @param jsonObject jsonObject whose field will be parsed.
 * @param fieldName the field name that will be used to parse the json object.
 */
function createHtmlColumn(row, jsonObject, fieldName) {
    let newCol = document.createElement("th")
    let value = jsonObject[fieldName]
    //if (fieldName === "long-description") {
    //    value = "long defintion"
    //}
    newCol.innerHTML = value
    row.appendChild(newCol)
}

/**
 * sets the table contents from the json object
 */
function setHeaderTable(jsonTableDisplay, tableElementOrder, jsonBlob) {

    for (let i = 0; i < jsonBlob.length; i++) {

        let jsonObject = jsonBlob[i]
        let newRow = document.createElement("tr")

        // create new columns for each of the elements in the json blob
        tableElementOrder.forEach((value, index) => createHtmlColumn(newRow, jsonObject, value))

        // create new row for every json object
        jsonTableDisplay.appendChild(newRow)
    }

}



