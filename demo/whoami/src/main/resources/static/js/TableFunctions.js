
/**
 * parses a field from a json blob,
 * creates a column with the field information.
 * adds the field to the row.
 *
 * @param row Table row object that will have a column appended to it.
 * @param jsonObject jsonObject whose field will be parsed.
 * @param fieldName the field name that will be used to parse the json object.
 */
function setTableColumns(row, jsonObject, fieldName) {
    let newCol = document.createElement("th")
    let value = jsonObject[fieldName]
    newCol.innerHTML = value
    row.appendChild(newCol)
}

/**
 * Sets the first row (the title row) of a table.
 *
 * @param jsonTableDisplay
 * @param tableElementOrder
 */
function setTitle(jsonTableDisplay, tableElementOrder) {

    let newRow = document.createElement("tr")
    for (let i = 0; i < tableElementOrder.length; i++) {
        let newCol = document.createElement("th")
        newCol.innerText = tableElementOrder[i]
        newRow.appendChild(newCol)
    }
    jsonTableDisplay.appendChild(newRow)
}

/**
 * sets the table contents from the json object
 */
function setTableRows(jsonTableDisplay, tableElementOrder, jsonBlob) {

    setTitle(jsonTableDisplay, tableElementOrder)

    for (let i = 0; i < jsonBlob.length; i++) {

        let jsonObject = jsonBlob[i]
        let newRow = document.createElement("tr")

        // create new columns for each of the elements in the json blob
        tableElementOrder.forEach((value, index) => setTableColumns(newRow, jsonObject, value))

        // create new row for every json object
        jsonTableDisplay.appendChild(newRow)
    }

}

function setTableColumnFromEntry(row, value) {
    let newCol = document.createElement("th")
    newCol.innerHTML = value
    row.appendChild(newCol)
}

function setTableTowsFromJsonWithoutFieldName(jsonTableDisplay, tableElementOrder, jsonBlob) {

    console.log("setting up the table: "+  jsonTableDisplay.toString())
    let blobEntries = Object.entries(jsonBlob)
    setTitle(jsonTableDisplay, tableElementOrder)

    for (let i = 0; i < blobEntries.length; i++) {

        let keyValueArray = blobEntries[i]
        let newRow = document.createElement("tr")
        setTableColumnFromEntry(newRow, keyValueArray[0])
        setTableColumnFromEntry(newRow, keyValueArray[1])
        jsonTableDisplay.appendChild(newRow)

    }

}