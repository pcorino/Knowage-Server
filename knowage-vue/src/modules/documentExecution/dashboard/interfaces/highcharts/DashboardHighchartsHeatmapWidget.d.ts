import { IHighchartsSerieAccessibility } from "./DashboardHighchartsWidget"

export interface IHighchartsHeatmapAxis {
    min: number | null,
    max: number | null,
    categories: string[],
    labels: IHighchartsHeatmapAxisLabels
    title: IHighchartsHeatmapAxisTitle
}

export interface IHighchartsHeatmapAxisLabels {
    rotation: number | null
    style: {
        fontFamily: string
        fontSize: string
        fontWeight: string
        color: string
    },
    format?: string,
    formatter?: Function,
    formatterText?: string,
    formatterError?: string,
}

export interface IHighchartsHeatmapAxisTitle {
    enabled: boolean,
    text: string,
    style: {
        fontFamily: string
        fontSize: string
        fontWeight: string
        color: string
    }
}

export interface IHighchartsHeatmapSerie {
    name: string,
    data: IHighchartsHeatmapSerieData[],
    accessibility?: IHighchartsSerieAccessibility
}

export interface IHighchartsHeatmapSerieData {
    id: number,
    x: number,
    y: number,
    value: number
}