import { IWidget, ITableWidgetColumnGroup, IDataset, IWidgetCrossNavigation } from '../../Dashboard'

export const getColumnGroup = (propWidget: IWidget, col: ITableWidgetColumnGroup) => {
    const modelGroups = propWidget.settings.configuration.columnGroups.groups
    if (propWidget.settings.configuration.columnGroups.enabled && modelGroups && modelGroups.length > 0) {
        for (const k in modelGroups) {
            if (modelGroups[k].columns.includes(col.id)) {
                return modelGroups[k]
            }
        }
    } else return false
}

export const getWidgetStyleByType = (propWidget: IWidget, styleType: string) => {
    const styleSettings = propWidget?.settings.style[styleType]
    if (styleSettings?.enabled) {
        const styleString = Object.entries(styleSettings.properties ?? styleSettings)
            .map(([k, v]) => `${k}:${v}`)
            .join(';')
        return styleString + ';'
    } else return ''
}

export const getWidgetStyleByTypeWithoutValidation = (propWidget: IWidget, styleType: string) => {
    const styleSettings = propWidget.settings.style[styleType]
    const styleString = Object.entries(styleSettings.properties ?? styleSettings)
        .map(([k, v]) => `${k}:${v}`)
        .join(';')
    return styleString + ';'
}

export const getColumnConditionalStyles = (propWidget: IWidget, colId: string, valueToCompare: any, returnString?: boolean) => {
    const conditionalStyles = propWidget.settings.conditionalStyles
    let styleString = null as any

    const columnConditionalStyles = conditionalStyles.conditions.filter((condition) => condition.target.includes(colId))
    if (columnConditionalStyles.length > 0) {
        for (let i = 0; i < columnConditionalStyles.length; i++) {
            if (isConditionMet(columnConditionalStyles[i].condition, valueToCompare)) {
                if (columnConditionalStyles[i].applyToWholeRow && !returnString) {
                    styleString = columnConditionalStyles[i].properties
                } else if (returnString) {
                    styleString = Object.entries(columnConditionalStyles[i].properties)
                        .map(([k, v]) => `${k}:${v}`)
                        .join(';')
                }
                break
            }
        }
    }
    return styleString
}

export const isConditionMet = (condition, valueToCompare) => {
    let fullfilledCondition = false
    switch (condition.operator) {
        case '==':
            fullfilledCondition = valueToCompare == condition.value
            break
        case '>=':
            fullfilledCondition = valueToCompare >= condition.value
            break
        case '<=':
            fullfilledCondition = valueToCompare <= condition.value
            break
        case 'IN':
            fullfilledCondition = condition.value.split(',').indexOf(valueToCompare) != -1
            break
        case '>':
            fullfilledCondition = valueToCompare > condition.value
            break
        case '<':
            fullfilledCondition = valueToCompare < condition.value
            break
        case '!=':
            fullfilledCondition = valueToCompare != condition.value
            break
    }
    return fullfilledCondition
}


export const createNewTableSelection = (value: (string | number)[], columnName: string, widget: IWidget, datasets: IDataset[]) => {
    return { datasetId: widget.dataset as number, datasetLabel: getDatasetLabel(widget.dataset as number, datasets) as string, columnName: columnName, value: value, aggregated: false, timestamp: new Date().getTime() }
}

const getDatasetLabel = (datasetId: number, datasets: IDataset[]) => {
    const index = datasets.findIndex((dataset: IDataset) => dataset.id.dsId == datasetId)
    return index !== -1 ? datasets[index].label : ''
}

export const isCrossNavigationActive = (tableNode: any, crossNavigationOptions: IWidgetCrossNavigation) => {
    console.log('--------- table node: ', tableNode)
    console.log('--------- crossNavigationOptions: ', crossNavigationOptions)
    if (!crossNavigationOptions.enabled) return false
    if ((crossNavigationOptions.type === 'singleColumn' && !crossNavigationOptions.column) || (tableNode.colDef?.columnName !== crossNavigationOptions.column)) return false
    return true
}

export const formatRowDataForCrossNavigation = (tableNode: any, dataToShow: any) => {
    const columnDefs = tableNode.columnApi?.columnModel?.columnDefs
    const rowData = tableNode.node.data
    if (!columnDefs || !rowData) return {}
    const formattedRow = {}
    columnDefs.forEach((columnDef: any) => formattedRow[columnDef.columnName] = { value: rowData[columnDef.field], type: getColumnType(columnDef.field, dataToShow) })
    return formattedRow
}

export const getFormattedClickedValueForCrossNavigation = (tableNode: any, dataToShow: any) => {
    return { value: tableNode.value, type: getColumnType(tableNode.colDef?.field, dataToShow) }
}

const getColumnType = (columnField: string, dataToShow: any) => {
    if (!dataToShow.metaData || !dataToShow.metaData.fields) return ''
    const index = dataToShow.metaData.fields.findIndex((field: any) => field.name === columnField)
    return index !== -1 ? dataToShow.metaData.fields[index].type : ''
}