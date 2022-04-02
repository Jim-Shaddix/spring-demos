
/**
 * Sets the first row (the title row) of a table.
 *
 * @param tableElement
 * @param columnTitles
 * @param columnWidths
 */
function setTitle(tableElement, columnTitles, columnWidths) {
    let newHead = document.createElement("thead")
    let newRow = document.createElement("tr")
    for (let i = 0; i < columnTitles.length; i++) {
        let newCol = document.createElement("th")
        newCol.innerText = columnTitles[i]
        newCol.setAttribute("scope", "col")
        if (columnWidths !== undefined) {
            newCol.style.width = columnWidths[i]
        }
        newRow.appendChild(newCol)
    }
    newHead.appendChild(newRow)
    tableElement.appendChild(newHead)
}

/**
 * append a column to a row
 *
 * @param row row that will have a column appended to it
 * @param value value to set in the column
 */
function appendColumn(row, value) {
    let newCol = document.createElement("td")
    if (value === null) {
        value = "null"
    }
    newCol.innerHTML = value
    row.appendChild(newCol)
}

/**
 * sets the table contents from the json object
 */
function populateTableFromObjectArray(tableElement, columnTitles, arrayObject) {

    console.log("setting up the table: "+  tableElement.id)

    setTitle(tableElement, columnTitles)

    for (let i = 0; i < arrayObject.length; i++) {

        let jsonObject = arrayObject[i]
        let newRow = document.createElement("tr")

        // create new columns for each of the elements in the json blob
        columnTitles.forEach((field, index) => {
            appendColumn(newRow, jsonObject[field])
        })

        // create new row for every json object
        tableElement.appendChild(newRow)
    }

}

function populateTableFromKeyValuePairs(tableElement, columnTitles, keyValueObject) {

    console.log("setting up the table: "+  tableElement.id)

    columnTitles.push("field-description")

    setTitle(tableElement, columnTitles)

    let description = keyValueObject["description"]

    let blobEntries = Object.entries(keyValueObject)
    for (let i = 0; i < blobEntries.length; i++) {

        let newRow = document.createElement("tr")

        // skipping the description column
        if (blobEntries[i][0] === "description"){continue}

        // getting column values to add
        let key = blobEntries[i][0]
        let value = blobEntries[i][1]
        let keyDescription = description[key + "-def"]

        // adding columns
        appendColumn(newRow, key)
        appendColumn(newRow, value)
        appendColumn(newRow, keyDescription)

        // appending row to the table
        tableElement.appendChild(newRow)

    }

}