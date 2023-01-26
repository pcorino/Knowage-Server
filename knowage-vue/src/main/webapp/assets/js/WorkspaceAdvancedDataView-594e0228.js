import{d as O,l as U,n as K,i as H,m as I,ai as P,s as z,_ as N,r,y as W,o as n,h as c,w as d,f as o,z as C,g,t as f,a as D,c as k,C as V,D as M,B as A,X as _,q as ee,A as y,V as T}from"./index-1e676509.js";import{f as te}from"./filterHelper-a723991a.js";import{w as B,E as ae,W as oe}from"./Workspace.store-75fa566b.js";import{m as re}from"./WorkspaceDescriptor-d4693b02.js";import{D as ne,W as ie}from"./WorkspaceCard-6d9360d0.js";import{s as se}from"./chip.esm-057ba1f1.js";import{s as le}from"./contextmenu.esm-4301c9fe.js";import{D as de,W as ce}from"./WorkspaceDataPreviewDialog-5099e5c1.js";import{W as pe}from"./WorkspaceWarningDialog-b684933b.js";import{C as be}from"./client-1808a2c5.js";import"./KnValidatonMessages-ebe48c72.js";import"./textarea.esm-32051d54.js";import"./sidebar.esm-2131cfd1.js";import"./KnScheduler-d2aec922.js";import"./calendar.esm-60a451ba.js";import"./checkbox.esm-fa6c8259.js";import"./radiobutton.esm-c3e6fa9b.js";import"./inputswitch.esm-80317a38.js";import"./KnParameterSidebar-7c5f3d6f.js";import"./chips.esm-693c3c01.js";import"./tree.esm-837306a6.js";import"./menu.esm-6bee86d0.js";import"./multiselect.esm-ce4000f7.js";const ke="960px",ue=[{field:"label",header:"common.label",style:{}},{field:"name",header:"common.name",style:{}},{field:"dsTypeCd",header:"common.type",style:{}},{field:"icon",header:"",style:{width:"40px"}}],he="stack",me=15,ge={iconColumn:{style:{"text-align":"end"}}};var fe={breakpoint:ke,columns:ue,responsiveLayout:he,rows:me,table:ge};const ve=O({name:"datasets-catalog-datatable",components:{Column:U,DataTable:K,Dialog:H},props:{items:[],visibility:Boolean},emits:["selected","save","cancel"],data(){return{KnDatasetListDescriptor:fe,datasets:[],filteredDatasets:[],selectedDataset:{},searchWord:"",loading:!1,isDatasetSelected:!1}},updated(){this.items&&(this.datasets=this.items),this.filteredDatasets=[...this.datasets]},computed:{...I(B,["isAvroLoaded","isAvroLoading"]),isEmpty(){return Object.keys(this.selectedDataset).length==0}},methods:{...P(z,["setInfo","setError"]),apply(){this.$emit("save",this.selectedDataset),this.clearForm()},cancel(){this.$emit("cancel",this.selectedDataset),this.clearForm()},clearForm(){this.selectedDataset={},this.isDatasetSelected=!1},searchDatasets(){setTimeout(()=>{this.searchWord.trim().length?this.filteredDatasets=this.datasets.filter(e=>e.label.toLowerCase().includes(this.searchWord.toLowerCase())||e.name.toLowerCase().includes(this.searchWord.toLowerCase())||e.dsTypeCd.toLowerCase().includes(this.searchWord.toLowerCase())):this.filteredDatasets=[...this.datasets]},250)},handleClick(e){this.$emit("selected",e),this.isDatasetSelected=!0}}}),we={id:"noDatasetsFound"},ye={class:"table-header p-d-flex"},De={class:"p-input-icon-left p-mr-3 p-col-12"},xe=D("i",{class:"pi pi-search"},null,-1),Ce={key:0},Me={key:1},Ae={key:2,style:{height:"1.57rem"}};function $e(e,t,i,s,u,b){const h=r("InputText"),p=r("Button"),$=r("Column"),E=r("DataTable"),L=r("Dialog"),x=W("tooltip"),S=W("t");return n(),c(L,{class:"kn-dialog--toolbar--primary datasetListDialogClass",visible:e.visibility,header:e.$t("components.advancedData.chooseDataset"),closable:!1,modal:"",breakpoints:{"960px":"75vw","640px":"100vw"}},{footer:d(()=>[o(p,{class:"kn-button kn-button--secondary",label:e.$t("common.cancel"),onClick:e.cancel},null,8,["label","onClick"]),C(o(p,{class:"kn-button kn-button--primary",onClick:e.apply,disabled:!e.isDatasetSelected},null,8,["onClick","disabled"]),[[S,"common.open"]])]),default:d(()=>[o(E,{id:"datasets-datatable",value:e.filteredDatasets,selection:e.selectedDataset,selectionMode:"single",paginator:!0,rows:e.KnDatasetListDescriptor.rows,loading:e.loading,class:"p-datatable-sm kn-table kn-page-content",dataKey:"id",responsiveLayout:e.KnDatasetListDescriptor.responsiveLayout,breakpoint:e.KnDatasetListDescriptor.breakpoint,onRowClick:t[1]||(t[1]=l=>e.handleClick(l.data))},{loading:d(()=>[g(f(e.$t("common.info.dataLoading")),1)]),empty:d(()=>[D("div",we,f(e.$t("managers.advancedData.noDatasetsFound")),1)]),header:d(()=>[D("div",ye,[D("span",De,[xe,o(h,{class:"kn-material-input",modelValue:e.searchWord,"onUpdate:modelValue":t[0]||(t[0]=l=>e.searchWord=l),type:"text",placeholder:e.$t("common.search"),onInput:e.searchDatasets},null,8,["modelValue","placeholder","onInput"])])])]),default:d(()=>[(n(!0),k(A,null,V(e.KnDatasetListDescriptor.columns,l=>(n(),c($,{class:"kn-truncated",style:M(l.style),header:e.$t(l.header),key:l.field,sortField:l.field,sortable:l.field!=="icon"},{body:d(v=>[l.field!=="icon"?C((n(),k("span",Ce,[g(f(v.data[l.field]),1)])),[[x,v.data[l.field],void 0,{top:!0}]]):(n(),k("span",Me,[e.isAvroLoaded(v.data.id)?C((n(),c(p,{key:0,icon:"far fa-circle-check",class:"p-button-link"},null,512)),[[x,e.$t("workspace.advancedData.avroReady"),void 0,{left:!0}]]):e.isAvroLoading(v.data.id)?C((n(),c(p,{key:1,icon:"fa-solid fa-spinner",class:"p-button-link"},null,512)),[[x,e.$t("workspace.advancedData.avroLoading"),void 0,{left:!0}]]):(n(),k("div",Ae))]))]),_:2},1032,["style","header","sortField","sortable"]))),128))]),_:1},8,["value","selection","rows","loading","responsiveLayout","breakpoint"])]),_:1},8,["visible","header"])}var Ee=N(ve,[["render",$e]]);const Le=O({components:{DataTable:K,KnDatasetList:Ee,Column:U,Chip:se,DataPreparationMonitoringDialog:de,EditPreparedDatasetDialog:ae,DetailSidebar:ne,WorkspaceCard:ie,KnFabButton:_,WorkspaceDataCloneDialog:oe,WorkspaceWarningDialog:pe,WorkspaceDataPreviewDialog:ce,Message:ee,Menu:le},emits:["toggleDisplayView"],props:{toggleCardDisplay:{type:Boolean}},computed:{...I(z,["user"]),...I(B,["dataPreparation","isAvroReady"]),isDatasetOwner(){return this.user.userId===this.selectedDataset.owner},canLoadData(){if(this.selectedDataset.actions){for(let e=0;e<this.selectedDataset.actions.length;e++)if(this.selectedDataset.actions[e].name=="loaddata")return!0}return!1}},data(){return{mainDescriptor:re,loading:!1,showDetailSidebar:!1,showDatasetList:!1,showEditPreparedDatasetDialog:!1,datasetList:[],preparedDatasets:[],availableDatasets:[],selectedDataset:{},selectedDsForDataPrep:{},menuButtons:[],creationMenuButtons:[],filters:{global:[te]},cloneDialogVisible:!1,shareDialogVisible:!1,previewDialogVisible:!1,warningDialogVisbile:!1,warningMessage:"",searchWord:"",showMonitoring:!1,client:{}}},async created(){var t;if((t=this.user)!=null&&t.functionalities.includes("DataPreparation")){var e=new URL(window.location.origin);e.protocol=e.protocol.replace("http","ws");let i=e+"knowage-data-preparation/ws?X-Kn-Authorization="+localStorage.getItem("token");this.client=new be({brokerURL:i,connectHeaders:{},heartbeatIncoming:4e3,heartbeatOutgoing:4e3}),this.client.onConnect=s=>{console.log(s),this.client.subscribe("/user/queue/prepare",u=>{if(u.body){let b=JSON.parse(u.body);b.statusOk?(this.setInfo({title:"Dataset prepared successfully"}),this.addToLoadedAvros(b.dsId),this.addToAvroDatasets(b.dsId)):this.setError({title:"Cannot prepare dataset",msg:b.errorMessage}),this.removeFromLoadingAvros(b.dsId)}else this.setError({title:"Websocket error",msg:"got empty message"})})},this.client.onStompError=function(s){console.log("Broker reported error: "+s.headers.message),console.log("Additional details: "+s.body)},this.client.activate()}await this.getAllAvroDataSets(),await this.getDatasets()},unmounted(){var e;((e=this.user)==null?void 0:e.functionalities.includes("DataPreparation"))&&this.client&&Object.keys(this.client).length>0&&(this.client.deactivate(),this.client={})},methods:{...P(z,["setInfo","setError"]),...P(B,["addToLoadedAvros","addToLoadingAvros","addToAvroDatasets","removeFromLoadingAvros","removeFromLoadedAvros","setAvroDatasets","setLoadedAvros"]),async updatePreparedDataset(e){this.showEditPreparedDatasetDialog=!1,this.selectedDataset.name=e.name,this.selectedDataset.description=e.description,this.selectedDataset.type="PreparedDataset",await this.$http({method:"POST",url:"/knowage/restful-services/selfservicedataset/update",data:this.selectedDataset,headers:{"Content-Type":"application/x-www-form-urlencoded","X-Disable-Errors":"true"},transformRequest:function(t){var i=[];for(var s in t)i.push(encodeURIComponent(s)+"="+encodeURIComponent(t[s]));return i.join("&")}}).then(()=>{this.setInfo({title:"Updated successfully"})}).catch(()=>{this.setError({title:"Save error",msg:"Cannot update Prepared Dataset"})}),await this.getDatasets()},async loadDataset(e){this.loading=!0,await this.$http.get(`/knowage/restful-services/1.0/datasets/dataset/id/${e}`).then(t=>{this.selectedDataset=t.data[0]}).catch(()=>{}),this.loading=!1},toggleDisplayView(){this.$emit("toggleDisplayView")},newDataPrep(e){this.selectedDsForDataPrep=e},showSidebar(e){this.selectedDataset=e,this.showDetailSidebar=!0},hideDataSetCatalog(){this.showDatasetList=!1,this.selectedDsForDataPrep={}},showDataSetCatalog(){this.$http.get("/knowage/restful-services/3.0/datasets/for-dataprep").then(e=>{this.availableDatasets=[...e.data.root],this.showDatasetList=!0},()=>{this.setError({title:"Error",msg:"Cannot load dataset list"})})},showMenu(e,t){this.selectedDataset=t,this.createMenuItems(t),this.$refs.optionsMenu.toggle(e)},createMenuItems(e){var i;let t=[];t.push({key:"0",label:this.$t("workspace.myData.xlsxExport"),icon:"fas fa-file-excel",command:()=>this.exportDataset(e,"xls"),visible:this.canLoadData&&this.selectedDataset.dsTypeCd!="File"},{key:"1",label:this.$t("workspace.myData.csvExport"),icon:"fas fa-file-csv",command:()=>this.exportDataset(e,"csv"),visible:this.canLoadData&&this.selectedDataset.dsTypeCd!="File"},{key:"4",label:this.$t("workspace.myData.deleteDataset"),icon:"fas fa-trash",command:()=>this.deleteDatasetConfirm(e),visible:this.isDatasetOwner}),(i=this.user)!=null&&i.functionalities.includes("DataPreparation")&&t.push({key:"2",label:this.$t("workspace.myData.openDataPreparation"),icon:"fas fa-cogs",command:()=>this.openDataPreparation(e),visible:!0},{key:"3",label:this.$t("workspace.myData.monitoring"),icon:"pi pi-chart-line",command:()=>this.handleMonitoring(e),visible:!0}),t=t.sort((s,u)=>s.key.localeCompare(u.key)),this.menuButtons=t},createCreationMenuButtons(){this.creationMenuButtons=[],this.creationMenuButtons.push({key:"0",label:this.$t("workspace.myData.prepareData"),visible:!0})},async previewDataset(e){await this.loadDataset(e.id),this.previewDialogVisible=!0},editDataset(){this.showEditPreparedDatasetDialog=!0},handleMonitoring(e){console.log(e),this.showMonitoring=!this.showMonitoring},async openDataPreparation(e){e.dsTypeCd=="Prepared"?await this.$http.get(`/knowage/restful-services/3.0/datasets/advanced/${e.id}`).then(t=>{let i=t.data.configuration.dataPrepInstanceId;this.$http.get(`/knowage-data-preparation/api/1.0/process/by-instance-id/${i}`).then(s=>{let u=s.data.definition,b=s.data.id,h=s.data.instance.dataSetId;this.isAvroReady(h)?this.$router.push({name:"data-preparation",params:{id:h,transformations:JSON.stringify(u),processId:b,instanceId:i,dataset:JSON.stringify(e)}}):this.generateAvro(h)})},()=>{this.setError({title:"Cannot open data preparation"})}):this.isAvroReady(e.id)?this.$router.push({name:"data-preparation",params:{id:e.id}}):await this.generateAvro(e.id)},async generateAvro(e){var t;await this.$http.post(`/knowage/restful-services/1.0/data-preparation/prepare/${e}`,{},{headers:{Accept:"application/json, text/plain, */*","Content-Type":"application/json;charset=UTF-8"}}).then(()=>{this.setInfo({title:this.$t("workspace.myData.isPreparing")}),this.addToLoadingAvros(e);let i=this.dataPreparation.loadedAvros.indexOf(e);i>=0&&this.removeFromLoadedAvros(i)}).catch(()=>{}),((t=this.user)==null?void 0:t.functionalities.includes("DataPreparation"))&&this.client&&Object.keys(this.client).length>0&&this.client.publish({destination:"/app/prepare",body:e})},async getAllAvroDataSets(){await this.$http.get("/knowage/restful-services/3.0/datasets/avro").then(e=>{this.setAvroDatasets(e.data),this.setLoadedAvros(e.data)}).catch(()=>{})},async exportDataset(e,t){this.loading=!0,await this.$http.post(`/knowage/restful-services/2.0/export/dataset/${e.id}/${t}`,{},{headers:{Accept:"application/json, text/plain, */*","Content-Type":"application/json;charset=UTF-8"}}).then(()=>{this.setInfo({title:this.$t("common.toast.updateTitle"),msg:this.$t("workspace.myData.exportSuccess")})}).catch(()=>{}),this.loading=!1},getFileType(e){switch(e){case"csv":return"text/csv";case"xls":return"application/vnd.ms-excel";case"xlsx":return"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}},shareDataset(){this.shareDialogVisible=!0},async handleDatasetShare(e){this.loading=!0;const t=e.catTypeId?`selfservicedataset/share/?catTypeId=${e.catTypeId}&id=${e.id}`:`selfservicedataset/share/?id=${e.id}`;await this.$http.post("/knowage/restful-services/"+t).then(()=>{this.setInfo({title:this.$t("common.toast.updateTitle"),msg:this.$t("common.toast.success")}),this.showDetailSidebar=!1,this.shareDialogVisible=!1,this.getDatasets()}).catch(()=>{}),this.loading=!1},async cloneDataset(e){await this.loadDataset(e.id),this.cloneDialogVisible=!0},async handleDatasetClone(e){await this.$http.post("/knowage/restful-services/1.0/datasets",e,{headers:{"X-Disable-Errors":"true"}}).then(()=>{this.setInfo({title:this.$t("common.toast.deleteTitle"),msg:this.$t("common.toast.success")}),this.showDetailSidebar=!1,this.cloneDialogVisible=!1,this.getDatasets()}).catch(t=>{this.warningDialogVisbile=!0,this.warningMessage=t})},datasetPreparation(e){this.$router.push({name:"data-preparation",params:{id:e.id}})},deleteDatasetConfirm(e){this.$confirm.require({message:this.$t("common.toast.deleteMessage"),header:this.$t("common.toast.deleteTitle"),icon:"pi pi-exclamation-triangle",accept:async()=>await this.deleteDataset(e)})},async deleteDataset(e){this.loading=!0,await this.$http.delete(`/knowage/restful-services/1.0/datasets/${e.label}`).then(()=>{this.setInfo({title:this.$t("common.toast.deleteTitle"),msg:this.$t("common.toast.success")}),this.showDetailSidebar=!1,this.getDatasets()}).catch(()=>{}),this.loading=!1},closeWarningDialog(){this.warningMessage="",this.warningDialogVisbile=!1},async getDatasets(){this.loading=!0,this.searchWord="",this.preparedDatasets=this.$http.get("/knowage/restful-services/3.0/datasets/advanced").then(e=>{this.datasetList=[...e.data.root],this.preparedDatasets=[...this.datasetList]}).finally(()=>this.loading=!1)},searchItems(){setTimeout(()=>{this.searchWord.trim().length?this.preparedDatasets=this.datasetList.filter(e=>{var t,i,s;return((t=e.label)==null?void 0:t.toLowerCase().includes(this.searchWord.toLowerCase()))||((i=e.name)==null?void 0:i.toLowerCase().includes(this.searchWord.toLowerCase()))||((s=e.dsTypeCd)==null?void 0:s.toLowerCase().includes(this.searchWord.toLowerCase()))}):this.preparedDatasets=[...this.datasetList]},250)},async updateDatasetAndSave(e){this.showMonitoring=!1,await this.$http.patch("/knowage-data-preparation/api/1.0/instance/"+e.instanceId,{config:e.config},{headers:{Accept:"application/json, */*"}}).then(()=>{this.loadDataset(this.selectedDataset.id),this.setInfo({title:this.$t("common.save"),msg:this.$t("common.toast.updateSuccess")})},()=>{this.setError({title:this.$t("common.error.saving"),msg:this.$t("managers.workspaceManagement.dataPreparation.errors.updatingSchedulation")})})}}}),Se={class:"p-d-flex p-flex-row p-ai-center"},Te={class:"kn-overflow"},Ie={key:0},Pe=g(" \u2002 "),ze={key:1,class:"p-grid p-m-2","data-test":"card-container"};function We(e,t,i,s,u,b){const h=r("DataPreparationMonitoringDialog"),p=r("Button"),$=r("KnFabButton"),E=r("Toolbar"),L=r("ProgressBar"),x=r("KnDatasetList"),S=r("InputText"),l=r("Column"),v=r("Chip"),R=r("DataTable"),j=r("Message"),X=r("WorkspaceCard"),J=r("DetailSidebar"),Q=r("EditPreparedDatasetDialog"),F=r("Menu"),q=r("WorkspaceDataCloneDialog"),Y=r("WorkspaceDataPreviewDialog"),G=r("WorkspaceWarningDialog"),Z=W("tooltip");return n(),k(A,null,[o(h,{visibility:e.showMonitoring,"onUpdate:visibility":t[0]||(t[0]=a=>e.showMonitoring=a),onClose:t[1]||(t[1]=a=>e.showMonitoring=!1),onSave:e.updateDatasetAndSave,dataset:e.selectedDataset},null,8,["visibility","onSave","dataset"]),o(E,{class:"kn-toolbar kn-toolbar--secondary"},{start:d(()=>[g(f(e.$t("workspace.advancedData.title")),1)]),end:d(()=>[e.toggleCardDisplay?(n(),c(p,{key:0,icon:"fas fa-list",class:"p-button-text p-button-rounded p-button-plain",onClick:e.toggleDisplayView},null,8,["onClick"])):y("",!0),e.toggleCardDisplay?y("",!0):(n(),c(p,{key:1,icon:"fas fa-th-large",class:"p-button-text p-button-rounded p-button-plain",onClick:e.toggleDisplayView},null,8,["onClick"])),o($,{icon:"fas fa-plus","data-test":"new-folder-button",onClick:e.showDataSetCatalog},null,8,["onClick"])]),_:1}),e.loading?(n(),c(L,{key:0,mode:"indeterminate",class:"kn-progress-bar p-ml-2","data-test":"progress-bar"})):y("",!0),o(x,{visibility:e.showDatasetList,items:e.availableDatasets,onSelected:e.newDataPrep,onSave:t[2]||(t[2]=a=>e.openDataPreparation(e.selectedDsForDataPrep)),onCancel:e.hideDataSetCatalog},null,8,["visibility","items","onSelected","onCancel"]),D("div",Se,[o(S,{class:"kn-material-input p-m-2",style:M(e.mainDescriptor.style.filterInput),modelValue:e.searchWord,"onUpdate:modelValue":t[3]||(t[3]=a=>e.searchWord=a),type:"text",placeholder:e.$t("common.search"),onInput:e.searchItems,"data-test":"search-input"},null,8,["style","modelValue","placeholder","onInput"])]),D("div",Te,[e.toggleCardDisplay?y("",!0):(n(),c(R,{key:0,class:"p-datatable-sm kn-table p-mx-2",value:e.preparedDatasets,loading:e.loading,dataKey:"objId",responsiveLayout:"stack",breakpoint:"600px","data-test":"datasets-table"},{empty:d(()=>[g(f(e.$t("common.info.noDataFound")),1)]),default:d(()=>[o(l,{field:"label",header:e.$t("importExport.catalogFunction.column.label"),class:"kn-truncated",sortable:!0},null,8,["header"]),o(l,{field:"name",header:e.$t("importExport.gallery.column.name"),class:"kn-truncated",sortable:!0},null,8,["header"]),o(l,{field:"tags",header:e.$t("importExport.gallery.column.tags"),sortable:!0},{body:d(a=>[a.data.tags.length>0?(n(),k("span",Ie,[(n(!0),k(A,null,V(a.data.tags,(m,w)=>(n(),c(v,{key:w},{default:d(()=>[g(f(m.name),1)]),_:2},1024))),128))])):y("",!0)]),_:1},8,["header"]),o(l,{style:M(e.mainDescriptor.style.iconColumn)},{header:d(()=>[Pe]),body:d(a=>[o(p,{icon:"fas fa-ellipsis-v",class:"p-button-link",onClick:T(m=>e.showMenu(m,a.data),["stop"])},null,8,["onClick"]),C(o(p,{icon:"fas fa-info-circle",class:"p-button-link",onClick:T(m=>e.showSidebar(a.data),["stop"]),"data-test":"info-button-"+a.data.name},null,8,["onClick","data-test"]),[[Z,e.$t("workspace.myModels.showInfo"),void 0,{left:!0}]]),o(p,{icon:"fas fa-eye",class:"p-button-link",onClick:T(m=>e.previewDataset(a.data),["stop"])},null,8,["onClick"])]),_:1},8,["style"])]),_:1},8,["value","loading"])),e.toggleCardDisplay?(n(),k("div",ze,[e.preparedDatasets.length===0?(n(),c(j,{key:0,class:"kn-flex p-m-2",severity:"info",closable:!1,style:M(e.mainDescriptor.style.message)},{default:d(()=>[g(f(e.$t("common.info.noDataFound")),1)]),_:1},8,["style"])):(n(!0),k(A,{key:1},V(e.preparedDatasets,(a,m)=>(n(),c(X,{key:m,viewType:"dataset",document:a,onPreviewDataset:e.previewDataset,onEditDataset:e.editDataset,onExportToXlsx:t[4]||(t[4]=w=>e.exportDataset(w,"xls")),onExportToCsv:t[5]||(t[5]=w=>e.exportDataset(w,"csv")),onShareDataset:e.shareDataset,onCloneDataset:e.cloneDataset,onDeleteDataset:e.deleteDatasetConfirm,onOpenDataPreparation:e.openDataPreparation,onOpenSidebar:e.showSidebar,onMonitoring:t[6]||(t[6]=w=>e.showMonitoring=!e.showMonitoring)},null,8,["document","onPreviewDataset","onEditDataset","onShareDataset","onCloneDataset","onDeleteDataset","onOpenDataPreparation","onOpenSidebar"]))),128))])):y("",!0)]),o(J,{visible:e.showDetailSidebar,viewType:"dataset",document:e.selectedDataset,onPreviewDataset:e.previewDataset,onEditDataset:e.editDataset,onExportToXlsx:t[7]||(t[7]=a=>e.exportDataset(a,"xls")),onExportToCsv:t[8]||(t[8]=a=>e.exportDataset(a,"csv")),onShareDataset:e.shareDataset,onCloneDataset:e.cloneDataset,onDeleteDataset:e.deleteDatasetConfirm,onOpenDataPreparation:e.openDataPreparation,onClose:t[9]||(t[9]=a=>e.showDetailSidebar=!1),onMonitoring:t[10]||(t[10]=a=>e.showMonitoring=!e.showMonitoring),"data-test":"detail-sidebar"},null,8,["visible","document","onPreviewDataset","onEditDataset","onShareDataset","onCloneDataset","onDeleteDataset","onOpenDataPreparation"]),o(Q,{dataset:e.selectedDataset,visible:e.showEditPreparedDatasetDialog,onSave:e.updatePreparedDataset,onCancel:t[11]||(t[11]=a=>e.showEditPreparedDatasetDialog=!1)},null,8,["dataset","visible","onSave"]),o(F,{id:"optionsMenu",ref:"optionsMenu",model:e.menuButtons},null,8,["model"]),o(F,{id:"creationMenu",ref:"creationMenu",model:e.creationMenuButtons},null,8,["model"]),o(q,{visible:e.cloneDialogVisible,propDataset:e.selectedDataset,onClose:t[12]||(t[12]=a=>e.cloneDialogVisible=!1),onClone:e.handleDatasetClone},null,8,["visible","propDataset","onClone"]),o(Y,{visible:e.previewDialogVisible,propDataset:e.selectedDataset,onClose:t[13]||(t[13]=a=>e.previewDialogVisible=!1)},null,8,["visible","propDataset"]),o(G,{visible:e.warningDialogVisbile,title:e.$t("workspace.advancedData.title"),warningMessage:e.warningMessage,onClose:e.closeWarningDialog},null,8,["visible","title","warningMessage","onClose"])],64)}var nt=N(Le,[["render",We]]);export{nt as default};
//# sourceMappingURL=WorkspaceAdvancedDataView-594e0228.js.map
