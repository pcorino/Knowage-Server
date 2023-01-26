import{s as $}from"./chip.esm-057ba1f1.js";import{_,d as D,X as U,a4 as I,s as N,r,y as B,o as i,c as f,a as n,f as d,w as b,g as m,t as p,h as k,A as v,G as g,B as F,C as P,z as T}from"./index-1e676509.js";import{s as j}from"./menu.esm-6bee86d0.js";const L=["name","jobStatus","kpiNames"],R={style:{"max-height":"80vh"}};var X={filterFields:L,listBox:R};const K=D({name:"kpi-scheduler",components:{Chip:$,FabButton:U,Listbox:I,Menu:j},data(){return{kpiSchedulerDescriptor:X,schedulerList:[],items:[],loading:!1,touched:!1}},setup(){return{store:N()}},async created(){await this.loadPage()},methods:{async loadAllSchedules(){this.loading=!0,await this.$http.get("/knowage/restful-services/1.0/kpi/listSchedulerKPI").then(t=>{this.schedulerList=t.data,this.schedulerList.sort((a,e)=>a.name.toUpperCase()>e.name.toUpperCase()?1:-1)}).finally(()=>this.loading=!1)},async loadPage(){this.loading=!0,await this.loadAllSchedules(),this.touched=!1,this.loading=!1},toggle(t,a){this.createMenuItems(a),this.$refs.menu.toggle(t)},createMenuItems(t){this.items=[],this.items.push({label:this.$t("common.clone"),icon:"pi pi-copy",command:()=>this.cloneSchedulerConfirm(t)}),this.items.push({label:this.$t("common.delete"),icon:"far fa-trash-alt",command:()=>this.deleteScheduleConfirm(t.id)})},playIcon(t){return t.toUpperCase()==="SUSPENDED"?"fa fa-play":"fa fa-pause"},cloneSchedulerConfirm(t){this.$confirm.require({header:this.$t("common.toast.cloneConfirmTitle"),accept:()=>this.showForm(t,!0)})},showForm(t,a){a=!!a;const e=t.id?`/kpi-scheduler/edit-kpi-schedule?id=${t.id}&clone=${a}`:"/kpi-scheduler/new-kpi-schedule";this.touched?this.$confirm.require({message:this.$t("common.toast.unsavedChangesMessage"),header:this.$t("common.toast.unsavedChangesHeader"),icon:"pi pi-exclamation-triangle",accept:()=>{this.touched=!1,this.$router.push(e)}}):this.$router.push(e)},startSchedule(t){var s,c;if(((s=t.jobStatus)==null?void 0:s.toUpperCase())==="EXPIRED")return;const a="?jobGroup=KPI_SCHEDULER_GROUP&triggerGroup=KPI_SCHEDULER_GROUP&jobName="+t.id+"&triggerName="+t.id,e=((c=t.jobStatus)==null?void 0:c.toUpperCase())==="SUSPENDED"?"resumeTrigger":"pauseTrigger";this.$http.post("/knowage/restful-services/scheduler/"+e+a).then(u=>{u.data.resp==="ok"&&(t.jobStatus=t.jobStatus==="SUSPENDED"?"ACTIVE":"SUSPENDED")})},deleteScheduleConfirm(t){this.$confirm.require({message:this.$t("common.toast.deleteMessage"),header:this.$t("common.toast.deleteTitle"),icon:"pi pi-exclamation-triangle",accept:()=>this.deleteSchedule(t)})},async deleteSchedule(t){await this.$http.delete(`/knowage/restful-services/1.0/kpi/${t}/deleteKpiScheduler`).then(()=>{this.store.setInfo({title:this.$t("common.toast.deleteTitle"),msg:this.$t("common.toast.deleteSuccess")}),this.$router.push("/kpi-scheduler"),this.loadPage()})},getBordersClass(t){switch(t){case"SUSPENDED":return"kn-list-item-warning";case"ACTIVE":return"kn-list-item-success";case"EXPIRED":return"kn-list-item-error"}}}}),V={class:"kn-page"},G={class:"kn-page-content p-grid p-m-0"},O={class:"kn-list--column p-col-4 p-sm-4 p-md-3 p-p-0"},Q={class:"kn-list-item-text"},q={class:"p-d-flex p-flex-row kn-truncated"},J=["onClick"],Y={class:"p-col-8 p-sm-8 p-md-9 p-p-0 p-m-0"};function H(t,a,e,s,c,u){const h=r("FabButton"),w=r("Toolbar"),x=r("ProgressBar"),y=r("Chip"),C=r("Button"),M=r("Menu"),A=r("Listbox"),E=r("router-view"),S=B("tooltip");return i(),f("div",V,[n("div",G,[n("div",O,[d(w,{class:"kn-toolbar kn-toolbar--primary"},{start:b(()=>[m(p(t.$t("kpi.kpiScheduler.title")),1)]),end:b(()=>[d(h,{icon:"fas fa-plus",onClick:t.showForm,"data-test":"new-button"},null,8,["onClick"])]),_:1}),t.loading?(i(),k(x,{key:0,mode:"indeterminate",class:"kn-progress-bar","data-test":"progress-bar"})):v("",!0),t.loading?v("",!0):(i(),k(A,{key:1,class:"kn-list",options:t.schedulerList,listStyle:t.kpiSchedulerDescriptor.listBox.style,filter:!0,filterPlaceholder:t.$t("common.search"),filterMatchMode:"contains",filterFields:t.kpiSchedulerDescriptor.filterFields,emptyFilterMessage:t.$t("common.info.noDataFound"),onChange:a[0]||(a[0]=o=>t.showForm(o.value)),"data-test":"scheduler-list"},{empty:b(()=>[m(p(t.$t("common.info.noDataFound")),1)]),option:b(o=>[n("div",{class:g(["kn-list-item",t.getBordersClass(o.option.jobStatus)]),"data-test":"list-item"},[n("div",Q,[n("div",null,[n("span",null,p(o.option.name),1)]),n("div",q,[(i(!0),f(F,null,P(o.option.kpiNames.split(","),(l,z)=>T((i(),k(y,{class:"p-m-1",key:z,label:l},null,8,["label"])),[[S,o.option.kpiNames,void 0,{top:!0}]])),128))])]),o.option.jobStatus.toUpperCase()!=="EXPIRED"?(i(),f("i",{key:0,class:g(t.playIcon(o.option.jobStatus)),onClick:l=>t.startSchedule(o.option)},null,10,J)):v("",!0),d(C,{class:"p-button-link p-button-sm",icon:"fa fa-ellipsis-v",onClick:l=>t.toggle(l,o.option),"aria-haspopup":"true","aria-controls":"overlay_menu","data-test":"menu-button"},null,8,["onClick"]),d(M,{ref:"menu",model:t.items,popup:!0,"data-test":"menu"},null,8,["model"])],2)]),_:1},8,["options","listStyle","filterPlaceholder","filterFields","emptyFilterMessage"]))]),n("div",Y,[d(E,{onTouched:a[1]||(a[1]=o=>t.touched=!0),onClosed:a[2]||(a[2]=o=>t.touched=!1),onInserted:t.loadPage},null,8,["onInserted"])])])])}var at=_(K,[["render",H],["__scopeId","data-v-4f9f493b"]]);export{at as default};
//# sourceMappingURL=KpiScheduler-e6fba91d.js.map
