import{d as S,i as z,_ as O,r as d,o as c,h as D,w as s,g as f,t as r,f as i,a as o,G,c as v,A as g,D as W,ad as F,a5 as Y,X as B,q as X,s as L,y as j,z as N,Y as H,V as x,B as _,C as R,a4 as Q}from"./index-1e676509.js";import{g as M}from"./GlossaryDefinitionDescriptor-620d1c10.js";import q from"./GlossaryDefinitionHint-d997d63d.js";import{s as J}from"./textarea.esm-32051d54.js";import{s as P}from"./tree.esm-837306a6.js";import{s as Z}from"./chip.esm-057ba1f1.js";import{K as tt,u as at,c as ot}from"./KnValidatonMessages-ebe48c72.js";import{s as et}from"./autocomplete.esm-bebdfcbd.js";import"./KnHint-6d455703.js";const rt={breakpoints:{"640px":"100vw","960px":"85vw"},style:{width:"70vw"}},nt={insert:{toastTitle:"common.toast.createTitle"},success:"common.toast.success",update:{toastTitle:"common.toast.updateTitle"}},it={column:{style:{width:"45%","word-break":"break-all"}},iconColumn:{style:{"text-align":"end",width:"5%"}}};var V={dialog:rt,operation:nt,table:it};const lt=S({name:"glossary-definition-node-dialog",components:{Dialog:z,Textarea:J},emits:["close","save"],props:{visible:{type:Boolean},selectedContent:{type:Object}},watch:{selectedContent(){this.loadContent()}},data(){return{glossaryDefinitionDialogDescriptor:V,content:{},contentNameDirty:!1}},computed:{descriptionHelp(){var t,a;return((a=(t=this.content.CONTENT_DS)==null?void 0:t.length)!=null?a:"0")+" / 500"},buttonDisabled(){var t;return((t=this.content.CONTENT_NM)==null?void 0:t.length)===0}},created(){this.loadContent()},methods:{loadContent(){this.contentNameDirty=!1,this.content={...this.selectedContent}}}}),dt={class:"p-field p-m-4"},st={class:"p-float-label"},ct={for:"contentName",class:"kn-material-input-label"},bt={key:0,class:"p-error p-grid p-mt-2"},pt={class:"p-field p-m-4"},kt={class:"p-float-label"},ut={for:"contentCode",class:"kn-material-input-label"},mt={class:"p-field p-m-4"},gt={class:"p-float-label"},vt={for:"contentDescription",class:"kn-material-input-label"},ht={id:"description-help"},ft={id:"description-help"};function yt(t,a,e,n,p,C){const h=d("InputText"),w=d("Textarea"),A=d("Button"),k=d("Dialog");return c(),D(k,{breakpoints:t.glossaryDefinitionDialogDescriptor.dialog.breakpoints,style:W(t.glossaryDefinitionDialogDescriptor.dialog.style),visible:t.visible,modal:!0,class:"p-fluid kn-dialog--toolbar--primary",closable:!1},{header:s(()=>[f(r(t.content.CONTENT_ID?t.$t("managers.glossary.glossaryDefinition.editNode"):t.$t("managers.glossary.glossaryDefinition.newNode")),1)]),footer:s(()=>[i(A,{class:"kn-button kn-button--primary",onClick:a[4]||(a[4]=m=>t.$emit("close"))},{default:s(()=>[f(r(t.$t("common.close")),1)]),_:1}),i(A,{label:t.$t("common.save"),onClick:a[5]||(a[5]=m=>t.$emit("save",t.content)),class:"kn-button kn-button--primary",disabled:t.buttonDisabled},null,8,["label","disabled"])]),default:s(()=>{var m;return[o("div",dt,[o("span",st,[i(h,{id:"contentName",class:G(["kn-material-input",{"p-invalid":((m=t.content.CONTENT_NM)==null?void 0:m.length)===0&&t.contentNameDirty}]),modelValue:t.content.CONTENT_NM,"onUpdate:modelValue":a[0]||(a[0]=u=>t.content.CONTENT_NM=u),modelModifiers:{trim:!0},max:"100",onBlur:a[1]||(a[1]=u=>t.contentNameDirty=!0)},null,8,["modelValue","class"]),o("label",ct,r(t.$t("common.name"))+" *",1)]),t.content.CONTENT_NM.length===0&&t.contentNameDirty?(c(),v("div",bt,r(t.$t("common.validation.required",{fieldName:t.$t("common.name")})),1)):g("",!0)]),o("div",pt,[o("span",kt,[i(h,{id:"contentCode",class:"kn-material-input",modelValue:t.content.CONTENT_CD,"onUpdate:modelValue":a[2]||(a[2]=u=>t.content.CONTENT_CD=u),modelModifiers:{trim:!0},max:"30"},null,8,["modelValue"]),o("label",ut,r(t.$t("managers.glossary.common.code")),1)])]),o("div",mt,[o("span",gt,[i(w,{id:"contentDescription",class:"kn-material-input",modelValue:t.content.CONTENT_DS,"onUpdate:modelValue":a[3]||(a[3]=u=>t.content.CONTENT_DS=u),modelModifiers:{trim:!0},autoResize:!0,maxLength:"500",rows:"1"},null,8,["modelValue"]),o("label",vt,r(t.$t("common.description")),1)]),o("div",ht,[o("small",ft,r(t.descriptionHelp),1)])])]}),_:1},8,["breakpoints","style","visible"])}var wt=O(lt,[["render",yt],["__scopeId","data-v-75da9389"]]);const xt=S({name:"glossary-definition-detail",components:{Card:F,Dropdown:Y,GlossaryDefinitionHint:q,GlossaryDefinitionNodeDialog:wt,FabButton:B,Message:X,Tree:P},props:{reloadTree:{type:Boolean}},emits:["addWord","infoClicked"],data(){return{glossaryDefinitionDescriptor:M,glossaries:[],selectedGlossaryId:null,selectedGlossary:null,originalGlossary:null,nodes:[],buttonVisible:[],searchWord:null,timer:null,expandedKeys:{},nodeDialogVisible:!1,selectedContent:{},selectedNode:{},dropzoneActive:[],showTree:!1,showHint:!0,loading:!1}},watch:{async reloadTree(){this.updateParentNode("HAVE_WORD_CHILD",!0),await this.listContents(this.selectedGlossaryId,this.selectedNode)}},setup(){return{store:L()}},async created(){await this.loadGlossaryList()},methods:{async loadGlossaryInfo(t,a){await this.loadGlossary(t),await this.listContents(t,a)},async loadGlossaryList(){await this.$http.get("/knowage/restful-services/1.0/glossary/listGlossary").then(t=>this.glossaries=t.data)},async loadGlossary(t){this.loading=!0,await this.$http.get(`/knowage/restful-services/1.0/glossary/getGlossary?GLOSSARY_ID=${t}`).then(a=>{this.selectedGlossary={...a.data,SaveOrUpdate:"Update"},this.originalGlossary={...a.data,SaveOrUpdate:"Update"},this.showTree=!0,this.showHint=!1}).finally(()=>this.loading=!1)},async listContents(t,a){if(this.loading=!0,(a==null?void 0:a.WORD_ID)||this.searchWord){this.loading=!1;return}const e=a?a.id:null;let n=[];await this.$http.get(`/knowage/restful-services/1.0/glossary/listContents?GLOSSARY_ID=${t}&PARENT_ID=${e}`).then(p=>{p.data.forEach(C=>n.push(this.createNode(C,a))),n.sort((C,h)=>C.label>h.label?1:-1)}),this.attachContentToTree(a,n),this.loading=!1},attachContentToTree(t,a){t?(t.children=[],t.children=a):(this.nodes=[],this.nodes=a)},createNode(t,a){var e,n;return{key:t.CONTENT_ID?`content-${t.CONTENT_ID}`:`word-${t.WORD_ID}`,id:(e=t.CONTENT_ID)!=null?e:t.WORD_ID,label:(n=t.CONTENT_NM)!=null?n:t.WORD,icon:t.WORD_ID&&!(t.HAVE_WORD_CHILD||t.HAVE_CONTENTS_CHILD)?"pi pi-circle-on":"",children:[],data:t,style:this.glossaryDefinitionDescriptor.node.style,leaf:!(t.HAVE_WORD_CHILD||t.HAVE_CONTENTS_CHILD),selectable:!(t.HAVE_WORD_CHILD||t.HAVE_CONTENTS_CHILD),parent:a}},async filterGlossaryTree(){this.timer&&(clearTimeout(this.timer),this.timer=null);let t=[];this.timer=setTimeout(()=>{var a;this.loading=!0,this.$http.get(`/knowage/restful-services/1.0/glossary/glosstreeLike?WORD=${this.searchWord}&GLOSSARY_ID=${(a=this.selectedGlossary)==null?void 0:a.GLOSSARY_ID}`).then(e=>t=e.data).finally(()=>{this.createGlossaryTree(t),this.loading=!1})},1e3)},createGlossaryTree(t){this.nodes=[],this.expandedKeys={},t.GlossSearch.SBI_GL_CONTENTS.forEach(a=>{var n;const e=this.createNode(a,null);(n=a.CHILD)==null||n.forEach(p=>{e.children.push(this.createNode(p,e))}),this.nodes.push(e)})},async saveWordConfirm(t,a){if(a.data.HAVE_CONTENTS_CHILD||a.data.WORD_ID)return;const e=JSON.parse(t.dataTransfer.getData("text/plain"));this.$confirm.require({message:this.$t("managers.glossary.glossaryDefinition.saveWordConfirmMessage"),header:this.$t("managers.glossary.glossaryDefinition.saveWordConfirmTitle"),icon:"pi pi-exclamation-triangle",accept:async()=>await this.saveWord(e,a)})},async saveWord(t,a){this.loading=!0,this.selectedNode=a,await this.$http.post("/knowage/restful-services/1.0/glossary/business/addContents",{GLOSSARY_ID:this.selectedGlossaryId,PARENT_ID:a.id,WORD_ID:t.WORD_ID}).then(async e=>{e.data.Status!=="NON OK"?(this.store.setInfo({title:this.$t("common.toast.createTitle"),msg:this.$t("common.toast.success")}),this.updateParentNode("HAVE_WORD_CHILD",!0),await this.listContents(this.selectedGlossaryId,a)):this.store.setError({title:this.$t("common.error.generic"),msg:this.$t(this.glossaryDefinitionDescriptor.translation[e.data.Message])})}).catch(e=>{this.store.setError({title:this.$t("common.error.generic"),msg:e})}).finally(()=>{this.loading=!1,this.dropzoneActive=[]})},async deleteNodeConfirm(t){this.$confirm.require({message:this.$t("managers.glossary.glossaryDefinition.deleteNodeConfirmMessage"),header:this.$t("common.toast.deleteTitle"),icon:"pi pi-exclamation-triangle",accept:async()=>await this.deleteNode(t)})},async deleteNode(t){this.loading=!0,this.selectedNode=t.parent;const a=t.data.CONTENT_ID?`1.0/glossary/business/deleteContents?CONTENTS_ID=${t.data.CONTENT_ID}`:`1.0/glossary/business/deleteContents?PARENT_ID=${t.parent.id}&WORD_ID=${t.data.WORD_ID}`;let e="";if(await this.$http.post("/knowage/restful-services/"+a,{}).then(n=>e=n.data.Status).catch(n=>{this.store.setError({title:this.$t("common.error.generic"),msg:n})}),e==="OK"){let n="HAVE_CONTENTS_CHILD";t.data.WORD_ID&&(n="HAVE_WORD_CHILD"),this.searchWord?await this.filterGlossaryTree():await this.listContents(this.selectedGlossaryId,t.parent),this.selectedNode.children.length===0&&this.updateParentNode(n,!1)}this.dropzoneActive=[],this.loading=!1},async showNodeDialog(t,a){this.selectedNode=t,a==="edit"?await this.loadContent(t.data.CONTENT_ID):this.selectedContent={CONTENT_ID:"",CONTENT_NM:"",CONTENT_CD:"",CONTENT_DS:"",GLOSSARY_ID:this.selectedGlossaryId,NEWCONT:!0,PARENT_ID:t?t.data.CONTENT_ID:null,SaveOrUpdate:"Save"},this.nodeDialogVisible=!0},async loadContent(t){this.loading=!0,await this.$http.get(`/knowage/restful-services/1.0/glossary/getContent?CONTENT_ID=${t}`).then(a=>this.selectedContent={...a.data,CONTENT_ID:t,SaveOrUpdate:"Update"}).finally(()=>this.loading=!1)},async saveContent(t){this.loading=!0;let a={status:"",message:""};await this.$http.post("/knowage/restful-services/1.0/glossary/business/addContents",t).then(e=>a={status:e.data.Status,message:e.data.Message}).catch(e=>{this.store.setError({title:this.$t("common.error.generic"),msg:e})}),await this.updateTree(a,t),this.loading=!1},async updateTree(t,a){t.status==="NON OK"?this.store.setError({title:this.$t("common.toast.createTitle"),msg:this.$t(this.glossaryDefinitionDescriptor.translation[t.message])}):(this.store.setInfo({title:this.$t("common.toast.createTitle"),msg:this.$t("common.toast.success")}),this.nodeDialogVisible=!1,this.updateParentNode("HAVE_CONTENTS_CHILD",!0),a.SaveOrUpdate==="Save"?await this.listContents(this.selectedGlossaryId,this.selectedNode):this.updateNode(a))},updateNode(t){var e;let a=null;for(let n=0;n<this.nodes.length&&(a=this.findNode(this.nodes[n],(e=this.selectedNode)==null?void 0:e.id),!a);n++);a&&(a.data=t,a.label=t.CONTENT_NM)},findNode(t,a){if(t.id===a)return t;if(t.children!=null){let e=null;for(let n=0;e==null&&n<t.children.length;n++)e=this.findNode(t.children[n],a);return e}return null},deleteGlossaryConfirm(){this.$confirm.require({message:this.$t("common.toast.deleteMessage"),header:this.$t("common.toast.deleteTitle"),icon:"pi pi-exclamation-triangle",accept:()=>{this.deleteGlossary()}})},async deleteGlossary(){this.loading=!0,await this.$http.post(`/knowage/restful-services/1.0/glossary/business/deleteGlossary?GLOSSARY_ID=${this.selectedGlossaryId}`).then(()=>{this.store.setInfo({title:this.$t("common.toast.deleteTitle"),msg:this.$t("common.toast.deleteSuccess")}),this.selectedGlossaryId=null,this.selectedGlossary=null,this.originalGlossary=null}),await this.loadGlossaryList(),this.loading=!1},addWord(t){this.selectedNode=t,this.$emit("addWord",{parent:t.data,glossaryId:this.selectedGlossaryId})},async addNewGlossary(t){var a,e,n,p;this.showTree=!1,this.showHint=!1,this.selectedGlossaryId=null,this.expandedKeys={},t==="Save"?(this.nodes=[],this.selectedGlossary={GLOSSARY_CD:"",GLOSSARY_DS:"",GLOSSARY_NM:"",NEWGLOSS:!0,SBI_GL_CONTENTS:[],SaveOrUpdate:"Save"}):(this.selectedGlossary={GLOSSARY_ID:(a=this.selectedGlossary)==null?void 0:a.GLOSSARY_ID,GLOSSARY_CD:(e=this.selectedGlossary)==null?void 0:e.GLOSSARY_CD,GLOSSARY_DS:(n=this.selectedGlossary)==null?void 0:n.GLOSSARY_DS,GLOSSARY_NM:this.$t("common.copyOf")+" "+((p=this.selectedGlossary)==null?void 0:p.GLOSSARY_NM)},await this.handleSaveGlossary()),this.originalGlossary={GLOSSARY_CD:"",GLOSSARY_DS:"",GLOSSARY_NM:""}},async handleSaveGlossary(){var e,n;if(this.loading=!0,!((e=this.selectedGlossary)!=null&&e.GLOSSARY_NM)||!this.glossaryChanged()){this.loading=!1;return}const t=(n=this.selectedGlossary)!=null&&n.SaveOrUpdate?"1.0/glossary/business/addGlossary":"1.0/glossary/business/cloneGlossary";let a={};await this.$http.post("/knowage/restful-services/"+t,this.selectedGlossary).then(p=>{a=p.data}).catch(p=>{this.store.setError({title:this.$t("common.error.generic"),msg:p})}),this.updateGlossaryList(a),this.loading=!1},async updateGlossaryList(t){var a;t.Status&&t.Status!=="NON OK"?(this.store.setInfo({title:this.$t("common.toast.createTitle"),msg:this.$t("common.toast.success")}),((a=this.selectedGlossary)==null?void 0:a.SaveOrUpdate)==="Update"?this.updateGlossary():(t.id&&this.selectedGlossary&&(this.selectedGlossary.GLOSSARY_ID=t.id,this.selectedGlossaryId=t.id,this.selectedGlossaryId&&await this.loadGlossaryInfo(this.selectedGlossaryId,null)),await this.loadGlossaryList()),this.showTree=!0,this.selectedGlossary&&(this.selectedGlossary.SaveOrUpdate="Update"),this.originalGlossary={...this.selectedGlossary}):this.store.setError({title:this.$t("common.error.generic"),msg:this.glossaryDefinitionDescriptor.translation[t.Message]?this.$t(this.glossaryDefinitionDescriptor.translation[t.Message]):""})},glossaryChanged(){var t,a,e,n,p,C;return((t=this.selectedGlossary)==null?void 0:t.GLOSSARY_NM)!==((a=this.originalGlossary)==null?void 0:a.GLOSSARY_NM)||((e=this.selectedGlossary)==null?void 0:e.GLOSSARY_CD)!==((n=this.originalGlossary)==null?void 0:n.GLOSSARY_CD)||((p=this.selectedGlossary)==null?void 0:p.GLOSSARY_DS)!==((C=this.originalGlossary)==null?void 0:C.GLOSSARY_DS)},updateGlossary(){const t=this.glossaries.findIndex(a=>{var e;return a.GLOSSARY_ID===((e=this.selectedGlossary)==null?void 0:e.GLOSSARY_ID)});this.glossaries[t]=this.selectedGlossary},updateGlossaryName(t){this.selectedGlossary&&(this.selectedGlossary.GLOSSARY_NM=t)},updateParentNode(t,a){let e=null;if(this.selectedNode)for(let n=0;n<this.nodes.length&&(e=this.findNode(this.nodes[n],this.selectedNode.id),!e);n++);e&&(e.data[t]=a)},setDropzoneClass(t,a){a.data.CONTENT_ID&&!a.data.HAVE_CONTENTS_CHILD&&(this.dropzoneActive[a.key]=t)}}}),Dt={class:"kn-page-content p-m-0"},Ct={class:"p-d-flex p-flex-row"},At={key:0},$t={class:"p-field p-d-flex p-ai-center"},Mt={class:"p-d-flex p-flex-column p-mr-2",id:"glossary-select-container"},Nt={for:"glossary",class:"kn-material-input-label"},Et={id:"glossary-help"},Tt={key:0,id:"code-container"},It={class:"p-float-label"},St={for:"code",class:"kn-material-input-label"},Ot={key:0,class:"p-field p-d-flex kn-flex"},Gt={class:"p-float-label kn-flex"},_t={for:"description",class:"kn-material-input-label"},Rt={key:0},zt={class:"p-d-flex p-flex-row p-m-3"},Wt=["onMouseover","onMouseleave","onDrop","onDragenter","onDragleave"],Lt={class:"node-label p-mr-2"};function Vt(t,a,e,n,p,C){const h=d("FabButton"),w=d("Toolbar"),A=d("ProgressBar"),k=d("Button"),m=d("Message"),u=d("Dropdown"),b=d("InputText"),T=d("Tree"),y=d("Card"),E=d("GlossaryDefinitionHint"),K=d("GlossaryDefinitionNodeDialog"),I=j("tooltip");return c(),v("div",Dt,[i(w,{class:"kn-toolbar kn-toolbar--primary p-m-0"},{start:s(()=>[f(r(t.$t("managers.glossary.glossaryDefinition.title")),1)]),end:s(()=>[i(h,{icon:"fas fa-plus",class:"fab-button",onClick:a[0]||(a[0]=l=>t.addNewGlossary("Save"))})]),_:1}),t.loading?(c(),D(A,{key:0,mode:"indeterminate",class:"kn-progress-bar"})):g("",!0),i(y,{class:"p-m-3"},{header:s(()=>[i(w,{class:"kn-toolbar kn-toolbar--secondary"},{start:s(()=>[f(r(t.$t("managers.glossary.glossaryDefinition.glossary")),1)]),end:s(()=>[o("div",Ct,[t.selectedGlossary&&t.selectedGlossaryId&&t.selectedGlossaryId!=-1?(c(),v("div",At,[i(k,{class:"kn-button p-button-text",onClick:a[1]||(a[1]=l=>t.addNewGlossary("Clone"))},{default:s(()=>[f(r(t.$t("common.clone")),1)]),_:1}),i(k,{class:"kn-button p-button-text",onClick:t.deleteGlossaryConfirm},{default:s(()=>[f(r(t.$t("common.delete")),1)]),_:1},8,["onClick"])])):g("",!0)])]),_:1})]),content:s(()=>[i(m,{class:"p-mt-0"},{default:s(()=>[f(r(t.$t("managers.glossary.glossaryDefinition.help")),1)]),_:1}),o("div",null,[o("div",$t,[o("div",Mt,[o("label",Nt,r(t.$t("managers.glossary.glossaryDefinition.title")),1),i(u,{id:"glossary",class:"kn-material-input",modelValue:t.selectedGlossaryId,"onUpdate:modelValue":a[2]||(a[2]=l=>t.selectedGlossaryId=l),options:t.glossaries,optionLabel:"GLOSSARY_NM",optionValue:"GLOSSARY_ID",editable:t.selectedGlossary,placeholder:t.$t("managers.glossary.glossaryDefinition.glossary"),onChange:a[3]||(a[3]=l=>t.loadGlossaryInfo(l.value,null)),onInput:a[4]||(a[4]=l=>t.updateGlossaryName(l.target.value)),onBlur:t.handleSaveGlossary},null,8,["modelValue","options","editable","placeholder","onBlur"]),o("small",Et,r(t.$t("managers.glossary.glossaryDefinition.glossaryHint")),1)]),t.selectedGlossary?(c(),v("div",Tt,[o("span",It,[i(b,{id:"code",class:"kn-material-input full-width",modelValue:t.selectedGlossary.GLOSSARY_CD,"onUpdate:modelValue":a[5]||(a[5]=l=>t.selectedGlossary.GLOSSARY_CD=l),modelModifiers:{trim:!0},onBlur:t.handleSaveGlossary},null,8,["modelValue","onBlur"]),o("label",St,r(t.$t("managers.glossary.common.code")),1)])])):g("",!0)]),t.selectedGlossary?(c(),v("div",Ot,[o("div",Gt,[i(b,{id:"description",class:"kn-material-input full-width",modelValue:t.selectedGlossary.GLOSSARY_DS,"onUpdate:modelValue":a[6]||(a[6]=l=>t.selectedGlossary.GLOSSARY_DS=l),modelModifiers:{trim:!0},onBlur:t.handleSaveGlossary},null,8,["modelValue","onBlur"]),o("label",_t,r(t.$t("common.description")),1)])])):g("",!0)]),t.selectedGlossary&&t.showTree?(c(),v("div",Rt,[i(w,{class:"kn-toolbar kn-toolbar--default"},{start:s(()=>[f(r(t.$tc("managers.glossary.common.word",2)),1)]),end:s(()=>[t.selectedGlossary&&t.selectedGlossaryId&&t.selectedGlossaryId!=-1?(c(),D(k,{key:0,class:"kn-button p-button-text",onClick:a[7]||(a[7]=l=>t.showNodeDialog(null,"new"))},{default:s(()=>[f(r(t.$t("managers.glossary.glossaryDefinition.addNode")),1)]),_:1})):g("",!0)]),_:1}),o("div",zt,[i(b,{id:"search-input",class:"kn-material-input",modelValue:t.searchWord,"onUpdate:modelValue":a[8]||(a[8]=l=>t.searchWord=l),placeholder:t.$t("common.search"),onInput:t.filterGlossaryTree,"data-test":"search-input"},null,8,["modelValue","placeholder","onInput"])]),i(T,{id:"glossary-tree",value:t.nodes,expandedKeys:t.expandedKeys,onNodeExpand:a[10]||(a[10]=l=>t.listContents(t.selectedGlossary.GLOSSARY_ID,l))},{default:s(l=>[o("div",{class:G(["p-d-flex p-flex-row p-ai-center",{dropzone:t.dropzoneActive[l.node.key]}]),onMouseover:$=>t.buttonVisible[l.node.key]=!0,onMouseleave:$=>t.buttonVisible[l.node.key]=!1,onDrop:$=>t.saveWordConfirm($,l.node),onDragover:a[9]||(a[9]=x(()=>{},["prevent"])),onDragenter:x($=>t.setDropzoneClass(!0,l.node),["prevent"]),onDragleave:x($=>t.setDropzoneClass(!1,l.node),["prevent"])},[o("span",Lt,r(l.node.label),1),N(o("div",null,[!l.node.data.HAVE_WORD_CHILD&&l.node.data.CONTENT_NM?N((c(),D(k,{key:0,icon:"pi pi-bars",class:"p-button-link p-button-sm p-p-0",onClick:x($=>t.showNodeDialog(l.node,"new"),["stop"])},null,8,["onClick"])),[[I,t.$t("managers.glossary.glossaryDefinition.addNode"),void 0,{top:!0}]]):g("",!0),!l.node.data.HAVE_CONTENTS_CHILD&&l.node.data.CONTENT_NM?N((c(),D(k,{key:1,icon:"pi pi-book",class:"p-button-link p-button-sm p-p-0",onClick:x($=>t.addWord(l.node),["stop"])},null,8,["onClick"])),[[I,t.$t("managers.glossary.glossaryDefinition.addWord"),void 0,{top:!0}]]):g("",!0),l.node.data.CONTENT_NM?N((c(),D(k,{key:2,icon:"pi pi-pencil",class:"p-button-link p-button-sm p-p-0",onClick:x($=>t.showNodeDialog(l.node,"edit"),["stop"])},null,8,["onClick"])),[[I,t.$t("common.edit"),void 0,{top:!0}]]):g("",!0),N(i(k,{icon:"pi pi-info-circle",class:"p-button-link p-button-sm p-p-0",onClick:x($=>t.$emit("infoClicked",l.node.data),["stop"])},null,8,["onClick"]),[[I,t.$t("managers.glossary.glossaryDefinition.showInfo"),void 0,{top:!0}]]),N(i(k,{icon:"far fa-trash-alt",class:"p-button-link p-button-sm p-p-0",onClick:x($=>t.deleteNodeConfirm(l.node),["stop"])},null,8,["onClick"]),[[I,t.$t("common.delete"),void 0,{top:!0}]])],512),[[H,t.buttonVisible[l.node.key]]])],42,Wt)]),_:1},8,["value","expandedKeys"])])):g("",!0)]),_:1}),t.showHint?(c(),D(E,{key:1})):g("",!0),i(K,{visible:t.nodeDialogVisible,selectedContent:t.selectedContent,onSave:t.saveContent,onClose:a[11]||(a[11]=l=>t.nodeDialogVisible=!1)},null,8,["visible","selectedContent","onSave"])])}var Ut=O(xt,[["render",Vt],["__scopeId","data-v-2b22032c"]]);const Yt=S({name:"glossary-definition-info-dialog",components:{Chip:Z,Dialog:z},emits:["close"],props:{visible:{type:Boolean},contentInfo:{type:Object}},data(){return{glossaryDefinitionDescriptor:M,glossaryDefinitionDialogDescriptor:V}}}),Bt={key:0},Ht={key:1},Kt={key:0},Ft={key:0},Xt={class:"p-mr-2"},jt={class:"p-d-flex p-flex-row p-flex-wrap"},Qt=o("span",null,null,-1),qt={class:"p-mr-2"},Jt={class:"p-d-flex p-flex-row p-flex-wrap"};function Pt(t,a,e,n,p,C){const h=d("Chip"),w=d("Button"),A=d("Dialog");return c(),v("div",null,[i(A,{header:t.$t("managers.glossary.glossaryDefinition.details"),breakpoints:t.glossaryDefinitionDialogDescriptor.dialog.breakpoints,style:W(t.glossaryDefinitionDialogDescriptor.dialog.style),visible:t.visible,modal:!0,class:"glossaryDefinitionDetail p-fluid kn-dialog--toolbar--primary",closable:!1},{footer:s(()=>[i(w,{class:"kn-button kn-button--primary",onClick:a[0]||(a[0]=k=>t.$emit("close"))},{default:s(()=>[f(r(t.$t("common.close")),1)]),_:1})]),default:s(()=>{var k;return[t.contentInfo&&t.contentInfo.CONTENT_ID?(c(),v("div",Bt,[o("ul",null,[o("li",null,[o("span",null,r(t.$t("common.name"))+":",1),o("span",null,r(t.contentInfo.CONTENT_NM),1)]),o("li",null,[o("span",null,r(t.$t("managers.glossary.common.code"))+":",1),o("span",null,r(t.contentInfo.CONTENT_CD),1)]),o("li",null,[o("span",null,r(t.$t("common.description"))+":",1),o("span",null,r(t.contentInfo.CONTENT_DS),1)])])])):(k=t.contentInfo)!=null&&k.WORD_ID?(c(),v("div",Ht,[o("ul",null,[o("li",null,[o("span",null,r(t.$tc("managers.glossary.common.word",1))+":",1),o("span",null,r(t.contentInfo.WORD),1)]),o("li",null,[o("span",null,r(t.$t("managers.glossary.common.status"))+":",1),t.contentInfo.STATE_NM?(c(),v("span",Kt,r(t.$t(t.glossaryDefinitionDescriptor.translation[t.contentInfo.STATE_NM])),1)):g("",!0)]),o("li",null,[o("span",null,r(t.$t("managers.glossary.common.category"))+":",1),t.contentInfo.CATEGORY_NM?(c(),v("span",Ft,r(t.$t(t.glossaryDefinitionDescriptor.translation[t.contentInfo.CATEGORY_NM])),1)):g("",!0)]),o("li",null,[o("span",null,r(t.$t("common.description"))+":",1),o("span",null,r(t.contentInfo.DESCR),1)]),o("li",null,[o("span",null,r(t.$t("managers.glossary.common.formula"))+":",1),o("span",null,r(t.contentInfo.FORMULA),1)]),o("li",null,[o("span",Xt,r(t.$t("managers.glossary.common.link"))+":",1),o("div",jt,[(c(!0),v(_,null,R(t.contentInfo.LINK,(m,u)=>(c(),D(h,{class:"p-m-1",key:u},{default:s(()=>[f(r(m.WORD),1)]),_:2},1024))),128))])]),o("li",null,[o("span",null,r(t.$t("managers.glossary.common.attributes"))+":",1),o("ul",null,[(c(!0),v(_,null,R(t.contentInfo.SBI_GL_WORD_ATTR,(m,u)=>(c(),v("li",{key:u},[o("span",null,r(m.ATTRIBUTE_NM)+":",1),Qt,o("ul",null,[o("li",qt,r(m.VALUE),1),o("div",Jt,[(c(!0),v(_,null,R(t.contentInfo.LINK,(b,T)=>(c(),D(h,{class:"p-m-1",key:T},{default:s(()=>[f(r(b.WORD),1)]),_:2},1024))),128))])])]))),128))])])])])):g("",!0)]}),_:1},8,["header","breakpoints","style","visible"])])}var Zt=O(Yt,[["render",Pt]]);const ta={word:[{fieldName:"WORD",validators:[{key:"required"},{key:"regex",validator:{type:"extendedAlphanumericRegex"}}]},{fieldName:"DESCR",validators:[{key:"required"},{key:"regex",validator:{type:"extendedAlphanumericRegex"}}]}]};var U={validations:ta};const aa=S({name:"edit-word",components:{Dialog:z,Dropdown:Y,AutoComplete:et,KnValidationMessages:tt},props:{visible:{type:Boolean,required:!0},propWord:{type:Object,required:!1},state:{type:Array,required:!0},category:{type:Array,required:!0},selectedGlossaryId:{type:Number}},emits:["close","saved","reloadTree"],data(){return{glossaryDefinitionDialogDescriptor:V,glossaryDefinitionDialogValidationDescriptor:U,glossaryDefinitionDescriptor:M,word:{},tState:[],tCategory:[],oldWordName:null,filteredWords:[],operation:"insert",v$:at()}},computed:{buttonDisabled(){return this.v$.$invalid},availableWords(){return this.word&&this.word.LINK?this.filteredWords.filter(t=>this.word&&this.word.LINK&&this.word.LINK.findIndex(a=>t.WORD_ID===a.WORD_ID)<0):this.filteredWords}},validations(){return{word:ot("word",U.validations.word)}},watch:{propWord(){this.v$.$reset(),this.word={...this.propWord},this.oldWordName=this.word.WORD},state(){this.tState=this.state.map(t=>({id:t.VALUE_ID,name:this.$t(M.translation[t.VALUE_NM])}))},category(){this.tCategory=this.category.map(t=>({id:t.VALUE_ID,name:this.$t(M.translation[t.VALUE_NM])}))}},setup(){return{store:L()}},mounted(){this.propWord&&(this.word={...this.propWord},this.oldWordName=this.word.WORD),this.tState=this.state.map(t=>({id:t.VALUE_ID,name:this.$t(M.translation[t.VALUE_NM])})),this.tCategory=this.state.map(t=>({id:t.VALUE_ID,name:this.$t(M.translation[t.VALUE_NM])}))},methods:{async saveWord(){var t;(t=this.word)!=null&&t.WORD_ID?(this.operation="update",this.word.oldWord={WORD_ID:this.word.WORD_ID,WORD:this.oldWordName},this.word.SaveOrUpdate="Update"):(this.operation="insert",this.word.NEWWORD=!0,this.word.SaveOrUpdate="Save"),await this.$http.post("/knowage/restful-services/1.0/glossary/business/addWord",this.word).then(a=>{this.$emit("saved"),this.word.PARENT&&this.saveContent(a.data.id),this.store.setInfo({title:this.$t(this.glossaryDefinitionDialogDescriptor.operation[this.operation].toastTitle),msg:this.$t(this.glossaryDefinitionDialogDescriptor.operation.success)})}).catch(a=>{this.store.setError({title:this.$t("managers.constraintManagment.saveError"),msg:a.message})})},closeDialog(){this.$emit("close")},async loadWords(t){this.$http.get("/knowage/restful-services/1.0/glossary/listWords?WORD="+t).then(a=>this.filteredWords=a.data)},searchWord(t){this.loadWords(t.query)},async saveContent(t){await this.$http.post("/knowage/restful-services/1.0/glossary/business/addContents",{GLOSSARY_ID:this.selectedGlossaryId,PARENT_ID:this.word.PARENT.CONTENT_ID,WORD_ID:t}).then(a=>{a.data.Status!=="NON OK"?this.$emit("reloadTree"):this.store.setError({title:this.$t("common.error.generic"),msg:this.$t(this.glossaryDefinitionDescriptor.translation[a.data.Message])})}).catch(a=>{this.store.setError({title:this.$t("common.error.generic"),msg:a})})}}}),oa={class:"p-mt-3"},ea={class:"p-fluid p-formgrid p-grid"},ra={class:"p-field p-col-4 p-mb-3"},na={class:"p-float-label"},ia={for:"word",class:"kn-material-input-label"},la={class:"p-field p-col-4"},da={class:"p-float-label"},sa={for:"status",class:"kn-material-input-label"},ca={class:"p-field p-col-4"},ba={class:"p-float-label"},pa={for:"category",class:"kn-material-input-label"},ka={class:"p-field p-col-12"},ua={class:"p-float-label"},ma={for:"description",class:"kn-material-input-label"},ga={class:"p-field p-col-12"},va={class:"p-float-label"},ha={for:"formula",class:"kn-material-input-label"},fa={class:"p-field p-col-12"},ya={class:"p-float-label"},wa={for:"link",class:"kn-material-input-label"};function xa(t,a,e,n,p,C){const h=d("InputText"),w=d("KnValidationMessages"),A=d("Dropdown"),k=d("AutoComplete"),m=d("Button"),u=d("Dialog");return c(),D(u,{header:t.$tc("managers.glossary.common.word",1),breakpoints:t.glossaryDefinitionDialogDescriptor.dialog.breakpoints,style:W(t.glossaryDefinitionDialogDescriptor.dialog.style),visible:t.visible,modal:!0,closable:!1,class:"p-fluid kn-dialog--toolbar--primary"},{footer:s(()=>[i(m,{label:t.$t("common.cancel"),onClick:t.closeDialog,class:"kn-button kn-button--secondary"},null,8,["label","onClick"]),i(m,{label:t.$t("common.save"),onClick:t.saveWord,class:"kn-button kn-button--primary",disabled:t.buttonDisabled},null,8,["label","onClick","disabled"])]),default:s(()=>[o("div",oa,[o("form",ea,[o("div",ra,[o("span",na,[i(h,{id:"word",class:G(["kn-material-input",{"p-invalid":t.v$.word.WORD.$invalid&&t.v$.word.WORD.$dirty}]),type:"text",modelValue:t.v$.word.WORD.$model,"onUpdate:modelValue":a[0]||(a[0]=b=>t.v$.word.WORD.$model=b),modelModifiers:{trim:!0},maxLength:"100",onBlur:a[1]||(a[1]=b=>t.v$.word.WORD.$touch())},null,8,["modelValue","class"]),o("label",ia,r(t.$t("managers.glossary.common.word",1))+" * ",1)]),i(w,{class:"p-mt-1",vComp:t.v$.word.WORD,additionalTranslateParams:{fieldName:t.$tc("managers.glossary.common.word",1)}},null,8,["vComp","additionalTranslateParams"])]),o("div",la,[o("span",da,[i(A,{id:"status",class:"kn-material-input",modelValue:t.word.STATE,"onUpdate:modelValue":a[2]||(a[2]=b=>t.word.STATE=b),options:t.tState,optionValue:"id",optionLabel:"name"},null,8,["modelValue","options"]),o("label",sa,r(t.$t("managers.glossary.common.status")),1)])]),o("div",ca,[o("span",ba,[i(A,{id:"category",class:"kn-material-input",modelValue:t.word.CATEGORY,"onUpdate:modelValue":a[3]||(a[3]=b=>t.word.CATEGORY=b),options:t.tCategory,optionValue:"id",optionLabel:"name"},null,8,["modelValue","options"]),o("label",pa,r(t.$t("common.category")),1)])]),o("div",ka,[o("span",ua,[i(h,{id:"description",class:G(["kn-material-input",{"p-invalid":t.v$.word.DESCR.$invalid&&t.v$.word.DESCR.$dirty}]),type:"text",modelValue:t.v$.word.DESCR.$model,"onUpdate:modelValue":a[4]||(a[4]=b=>t.v$.word.DESCR.$model=b),maxLength:"500",onBlur:a[5]||(a[5]=b=>t.v$.word.DESCR.$touch())},null,8,["modelValue","class"]),o("label",ma,r(t.$t("common.description"))+" *",1)]),i(w,{class:"p-mt-1",vComp:t.v$.word.DESCR,additionalTranslateParams:{fieldName:t.$t("common.description")}},null,8,["vComp","additionalTranslateParams"])]),o("div",ga,[o("span",va,[i(h,{id:"formula",class:"kn-material-input",type:"text",modelValue:t.word.FORMULA,"onUpdate:modelValue":a[6]||(a[6]=b=>t.word.FORMULA=b),maxLength:"500"},null,8,["modelValue"]),o("label",ha,r(t.$t("managers.glossary.common.formula")),1)])]),o("div",fa,[o("span",ya,[i(k,{id:"link",class:"kn-material-input",multiple:!0,modelValue:t.word.LINK,"onUpdate:modelValue":a[7]||(a[7]=b=>t.word.LINK=b),suggestions:t.availableWords,onComplete:a[8]||(a[8]=b=>t.searchWord(b)),field:"WORD"},null,8,["modelValue","suggestions"]),o("label",wa,r(t.$t("managers.glossary.common.link")),1)])])])])]),_:1},8,["header","breakpoints","style","visible"])}var Da=O(aa,[["render",xa]]);const Ca=S({name:"glossary-definition",components:{FabButton:B,Listbox:Q,GlossaryDefinitionDetail:Ut,GlossaryDefinitionInfoDialog:Zt,GlossaryDefinitionWordEdit:Da},data(){return{glossaryDefinitionDescriptor:M,wordsList:[],contentInfo:null,infoDialogVisible:!1,state:[],category:[],selectedGlossaryId:null,user:{},reloadTree:!1,loading:!1,editWordDialogVisible:!1}},setup(){return{store:L()}},async created(){await this.loadPage()},methods:{async loadPage(){this.loading=!0,await this.loadWordsList(),await this.loadState(),await this.loadCategory(),this.loading=!1},async loadWordsList(){await this.$http.get("/knowage/restful-services/1.0/glossary/listWords?Page=1&ItemPerPage=").then(t=>this.wordsList=t.data)},async loadState(){await this.$http.get("/knowage/restful-services/domains/listValueDescriptionByType?DOMAIN_TYPE=GLS_STATE").then(t=>this.state=t.data)},async loadCategory(){await this.$http.get("/knowage/restful-services/domains/listValueDescriptionByType?DOMAIN_TYPE=GLS_CATEGORY").then(t=>this.category=t.data)},async showInfo(t){this.loading=!0;const a=t.CONTENT_ID?`1.0/glossary/getContent?CONTENT_ID=${t.CONTENT_ID}`:`1.0/glossary/getWord?WORD_ID=${t.WORD_ID}`;await this.$http.get("/knowage/restful-services/"+a).then(e=>{this.contentInfo=e.data,this.infoDialogVisible=!0}).finally(()=>this.loading=!1)},deleteWordConfirm(t){this.$confirm.require({message:this.$t("common.toast.deleteMessage"),header:this.$t("common.toast.deleteTitle"),icon:"pi pi-exclamation-triangle",accept:()=>{this.deleteWord(t)}})},async deleteWord(t){await this.$http.post(`/knowage/restful-services/1.0/glossary/business/deleteWord?WORD_ID=${t}`).then(()=>{this.store.setInfo({title:this.$t("common.toast.deleteTitle"),msg:this.$t("common.toast.deleteSuccess")}),this.$router.push("/glossary-definition"),this.loadWordsList()})},onDragStart(t,a){t.dataTransfer.setData("text/plain",JSON.stringify(a)),t.dataTransfer.dropEffect="move",t.dataTransfer.effectAllowed="move"},async editWord(t,a){t!=-1?await this.$http.get(`/knowage/restful-services/1.0/glossary/getWord?WORD_ID=${t}`).then(e=>{this.contentInfo=e.data}):this.contentInfo={LINK:[],SBI_GL_WORD_ATTR:[],STATE:"",CATEGORY:"",FORMULA:""},a&&(this.contentInfo.PARENT=a.parent,this.selectedGlossaryId=a.glossaryId),this.editWordDialogVisible=!0},wordSaved(){this.editWordDialogVisible=!1,this.loadWordsList()}}}),Aa={class:"kn-page"},$a={class:"kn-page-content p-grid p-m-0"},Ma={class:"p-col-8 p-sm-8 p-md-9 p-p-0 p-m-0 kn-page"},Na={class:"kn-list--column kn-list-border-left p-col-4 p-sm-4 p-md-3 p-p-0"},Ea=["onDragstart"],Ta=o("i",{class:"pi pi-bars"},null,-1),Ia=["onClick"];function Sa(t,a,e,n,p,C){const h=d("GlossaryDefinitionInfoDialog"),w=d("GlossaryDefinitionDetail"),A=d("FabButton"),k=d("Toolbar"),m=d("ProgressBar"),u=d("Button"),b=d("Listbox"),T=d("GlossaryDefinitionWordEdit");return c(),v("div",Aa,[o("div",$a,[N(i(h,{visible:t.infoDialogVisible,contentInfo:t.contentInfo,onClose:a[0]||(a[0]=y=>t.infoDialogVisible=!1)},null,8,["visible","contentInfo"]),[[H,t.infoDialogVisible]]),o("div",Ma,[i(w,{reloadTree:t.reloadTree,onInfoClicked:t.showInfo,onAddWord:a[1]||(a[1]=y=>t.editWord(-1,y))},null,8,["reloadTree","onInfoClicked"])]),o("div",Na,[i(k,{class:"kn-toolbar kn-toolbar--secondary"},{start:s(()=>[f(r(t.$t("managers.glossary.glossaryDefinition.wordsList")),1)]),end:s(()=>[i(A,{icon:"fas fa-plus",onClick:a[2]||(a[2]=x(y=>t.editWord(-1),["stop"])),"data-test":"new-button"})]),_:1}),t.loading?(c(),D(m,{key:0,mode:"indeterminate",class:"kn-progress-bar","data-test":"progress-bar"})):g("",!0),t.loading?g("",!0):(c(),D(b,{key:1,class:"kn-list--column",options:t.wordsList,filter:!0,filterPlaceholder:t.$t("common.search"),filterMatchMode:"contains",filterFields:t.glossaryDefinitionDescriptor.filterFields,emptyFilterMessage:t.$t("common.info.noDataFound"),"data-test":"words-list"},{empty:s(()=>[f(r(t.$t("common.info.noDataFound")),1)]),option:s(y=>[o("div",{class:"kn-list-item kn-draggable",draggable:"true",onDragstart:E=>t.onDragStart(E,y.option),"data-test":"list-item"},[Ta,o("div",{class:"kn-list-item-text",onClick:x(E=>t.editWord(y.option.WORD_ID),["stop"])},[o("span",null,r(y.option.WORD),1)],8,Ia),i(u,{icon:"pi pi-info-circle",class:"p-button-text p-button-rounded p-button-plain",onClick:x(E=>t.showInfo(y.option),["stop"]),"data-test":"info-button"},null,8,["onClick"]),i(u,{icon:"far fa-trash-alt",class:"p-button-text p-button-rounded p-button-plain",onClick:x(E=>t.deleteWordConfirm(y.option.WORD_ID),["stop"]),"data-test":"delete-button"},null,8,["onClick"])],40,Ea)]),_:1},8,["options","filterPlaceholder","filterFields","emptyFilterMessage"]))])]),i(T,{visible:t.editWordDialogVisible,onClose:a[3]||(a[3]=y=>t.editWordDialogVisible=!1),onSaved:t.wordSaved,state:t.state,category:t.category,propWord:t.contentInfo,selectedGlossaryId:t.selectedGlossaryId,onReloadTree:a[4]||(a[4]=y=>t.reloadTree=!t.reloadTree)},null,8,["visible","onSaved","state","category","propWord","selectedGlossaryId"])])}var Ya=O(Ca,[["render",Sa]]);export{Ya as default};
//# sourceMappingURL=GlossaryDefinition-fdb7bc6b.js.map
