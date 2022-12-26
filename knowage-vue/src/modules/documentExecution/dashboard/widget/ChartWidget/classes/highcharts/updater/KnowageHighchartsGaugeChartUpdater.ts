import { IHighchartsChartModel } from '@/modules/documentExecution/dashboard/interfaces/highcharts/DashboardHighchartsWidget'
import { getForamttedLabels, getFormattedLegend, getFormattedNoDataConfiguration, getFormattedSeries, getFormattedTooltipSettings } from './KnowageHighchartsCommonUpdater'

export const updateGaugeChartModel = (oldModel: any, newModel: IHighchartsChartModel) => {
    console.log(">>>>>>> OLD MODEL: ", oldModel)
    console.log(">>>>>>> NEW MODEL: ", newModel)
    getFormattedNoDataConfiguration(oldModel, newModel)
    getFormattedLegend(oldModel, newModel)
    getForamttedLabels(oldModel, newModel)
    getFormattedSeries(oldModel, newModel)
    getFormattedTooltipSettings(oldModel, newModel)
    getFormattedPaneSettings(oldModel, newModel)
    if (oldModel.CHART.AXES_LIST && oldModel.CHART.AXES_LIST.AXIS && oldModel.CHART.AXES_LIST.AXIS[0]) {
        getFormattedScaleSettings(oldModel, newModel)
    }

    return newModel
}

export const createSerie = (serieName: string, groupingFunction: string) => {
    return {
        name: serieName,
        colorByPoint: true,
        groupingFunction: groupingFunction,
        data: [],
        accessibility: {
            enabled: false,
            description: '',
            exposeAsGroupOnly: false,
            keyboardNavigation: { enabled: false }
        }
    }
}

const getFormattedPaneSettings = (oldModel: any, newModel: IHighchartsChartModel) => {
    if (oldModel.CHART.PANE) {
        newModel.pane = {
            startAngle: oldModel.CHART.PANE.startAngle,
            endAngle: oldModel.CHART.PANE.endAngle,
            center: newModel.pane.center
        }
    }
}


const getFormattedScaleSettings = (oldModel: any, newModel: IHighchartsChartModel) => {
    const oldYAxis = oldModel.CHART.AXES_LIST.AXIS[0]
    newModel.yAxis.min = oldYAxis.min
    newModel.yAxis.max = oldYAxis.max
}