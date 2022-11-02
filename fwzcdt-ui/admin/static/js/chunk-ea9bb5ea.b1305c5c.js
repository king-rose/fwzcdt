(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ea9bb5ea"],{1104:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"68px"}},[a("el-form-item",{attrs:{label:"任务ID",prop:"taskId"}},[a("el-input",{attrs:{placeholder:"请输入任务ID",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.taskId,callback:function(t){e.$set(e.queryParams,"taskId",t)},expression:"queryParams.taskId"}})],1),a("el-form-item",{attrs:{label:"任务名称",prop:"taskName"}},[a("el-input",{attrs:{placeholder:"任务名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.taskName,callback:function(t){e.$set(e.queryParams,"taskName",t)},expression:"queryParams.taskName"}})],1),a("el-form-item",{attrs:{label:"流程实例",prop:"instanceId"}},[a("el-input",{attrs:{placeholder:"请输入流程实例ID",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.instanceId,callback:function(t){e.$set(e.queryParams,"instanceId",t)},expression:"queryParams.instanceId"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"cyan",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.taskList},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),a("el-table-column",{attrs:{label:"任务ID",align:"center",prop:"taskId",width:"150"}}),a("el-table-column",{attrs:{label:"任务名称",align:"center",prop:"taskName",width:"150"}}),a("el-table-column",{attrs:{label:"流程实例ID",align:"center",prop:"instanceId",width:"150"}}),a("el-table-column",{attrs:{label:"待办人",align:"center",prop:"assigneeName",width:"150"}}),a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",width:"180"}}),a("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.instanceId?a("apply-after",{attrs:{row:t.row.formData,taskId:t.row.taskId,taskName:t.row.taskName,type:t.row.type},on:{getList:e.getList}}):e._e()]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1)},s=[],o=(a("d81d"),a("b775")),l=a("d259"),n={name:"Todo",components:{ApplyAfter:l["a"]},data:function(){return{loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,taskList:[],title:"",open:!1,queryParams:{pageNum:1,pageSize:10,taskId:null,taskName:null},form:{},rules:{}}},created:function(){this.getList()},methods:{getList:function(){var e=this;return this.loading=!0,Object(o["a"])({url:"/activiti/process/taskList",method:"get",params:this.queryParams}).then((function(t){e.taskList=t.rows,e.total=t.total,e.loading=!1})).then((function(){}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={id:null,taskId:null,taskName:null},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.reset(),this.open=!0,this.title="添加待办"},handleUpdate:function(e){var t=this;this.reset();var a=e.id||this.ids;getDemo(a).then((function(e){t.form=e.data,t.open=!0,t.title="修改待办"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(null!=e.form.id?updateDemo(e.form).then((function(t){e.msgSuccess("修改成功"),e.open=!1,e.getList()})):addDemo(e.form).then((function(t){e.msgSuccess("新增成功"),e.open=!1,e.getList()})))}))},handleDelete:function(e){var t=this,a=e.id||this.ids;this.$confirm('是否确认删除待办编号为"'+a+'"的数据项?',"警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return delDemo(a)})).then((function(){t.getList(),t.msgSuccess("删除成功")}))},handleExport:function(){var e=this,t=this.queryParams;this.$confirm("是否确认导出所有待办数据项?","警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return exportDemo(t)})).then((function(t){e.download(t.msg)}))}}},i=n,m=a("2877"),c=Object(m["a"])(i,r,s,!1,null,null,null);t["default"]=c.exports},"134f":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"请假类型",prop:"type"}},[a("el-select",{attrs:{disabled:"调整申请"!==e.taskName},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.typeOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1),a("el-form-item",{attrs:{label:"标题",prop:"title"}},[a("el-input",{attrs:{readonly:"调整申请"!==e.taskName},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),a("el-form-item",{attrs:{label:"原因",prop:"reason"}},[a("el-input",{attrs:{type:"textarea",readonly:"调整申请"!==e.taskName},model:{value:e.form.reason,callback:function(t){e.$set(e.form,"reason",t)},expression:"form.reason"}})],1),a("el-form-item",{attrs:{label:"开始时间",prop:"leaveStartTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:"调整申请"!==e.taskName},model:{value:e.form.leaveStartTime,callback:function(t){e.$set(e.form,"leaveStartTime",t)},expression:"form.leaveStartTime"}})],1),a("el-form-item",{attrs:{label:"结束时间",prop:"leaveEndTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:"调整申请"!==e.taskName},model:{value:e.form.leaveEndTime,callback:function(t){e.$set(e.form,"leaveEndTime",t)},expression:"form.leaveEndTime"}})],1),"销假"===e.taskName||"todo"!==e.type?a("el-form-item",{attrs:{label:"实际开始时间",prop:"realityStartTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择实际开始时间",readonly:"销假"!==e.taskName},model:{value:e.form.realityStartTime,callback:function(t){e.$set(e.form,"realityStartTime",t)},expression:"form.realityStartTime"}})],1):e._e(),"销假"===e.taskName||"todo"!==e.type?a("el-form-item",{attrs:{label:"实际结束时间",prop:"realityEndTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择实际结束时间",readonly:"销假"!==e.taskName},model:{value:e.form.realityEndTime,callback:function(t){e.$set(e.form,"realityEndTime",t)},expression:"form.realityEndTime"}})],1):e._e(),a("el-form-item",{attrs:{label:"申请人",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.applyUserName,callback:function(t){e.$set(e.form,"applyUserName",t)},expression:"form.applyUserName"}})],1),a("el-form-item",{attrs:{label:"申请时间",prop:"applyTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:""},model:{value:e.form.applyTime,callback:function(t){e.$set(e.form,"applyTime",t)},expression:"form.applyTime"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{type:"textarea",readonly:"调整申请"!==e.taskName},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1)],1),a("view-verify",{attrs:{open:e.open,row:e.row,taskId:e.taskId,type:e.type},on:{"update:open":function(t){e.open=t}}})],1)],1)},s=[],o=a("3657"),l={name:"LeaveForm",components:{ViewVerify:o["a"]},props:{row:{type:Object},taskId:{type:String},taskName:{type:String},type:{type:String,default:"none"}},data:function(){return{title:"查看请假表单",form:this.row,open:!1,typeOptions:[],rules:{type:[{required:!0,message:"请假类型不能为空",trigger:"blur"}],title:[{required:!0,message:"标题不能为空",trigger:"blur"}],reason:[{required:!0,message:"原因不能为空",trigger:"blur"}],leaveStartTime:[{required:!0,message:"开始时间不能为空",trigger:"blur"}],leaveEndTime:[{required:!0,message:"结束时间不能为空",trigger:"blur"}],realityStartTime:[{required:!0,message:"实际开始时间不能为空",trigger:"blur"}],realityEndTime:[{required:!0,message:"实际结束时间不能为空",trigger:"blur"}]}}},created:function(){var e=this;this.getDicts("biz_leave_type").then((function(t){e.typeOptions=t.data}))}},n=l,i=a("2877"),m=Object(i["a"])(n,r,s,!1,null,"5e7bb57e",null);t["default"]=m.exports},"1d85":function(e,t,a){"use strict";a("27fd")},"27fd":function(e,t,a){},3657:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{directives:[{name:"show",rawName:"v-show",value:"todo"===e.type,expression:"type === 'todo'"}]},[a("el-form",{attrs:{"label-width":"100px"}},[a("el-divider"),a("el-form-item",{attrs:{label:"审批意见"}},[a("el-radio-group",{model:{value:e.pass,callback:function(t){e.pass=t},expression:"pass"}},[a("el-radio",{attrs:{label:"true"}},[e._v("同意")]),a("el-radio",{attrs:{label:"false"}},[e._v("驳回")])],1)],1),a("el-form-item",{attrs:{label:"批注",prop:"remark"}},[a("el-input",{attrs:{type:"textarea",value:e.comment},model:{value:e.comment,callback:function(t){e.comment=t},expression:"comment"}})],1)],1)],1),a("div",{staticClass:"dialog-footer",staticStyle:{"text-align":"right"},attrs:{slot:"footer"},slot:"footer"},[a("el-button",{directives:[{name:"show",rawName:"v-show",value:"todo"===e.type,expression:"type === 'todo'"}],attrs:{type:"primary"},on:{click:e.complete}},[e._v("确 定")]),a("el-button",{on:{click:e.close}},[e._v("关 闭")])],1)])},s=[],o=(a("e9c4"),a("b775")),l={name:"ViewVerify",props:{row:{type:Object},taskId:{type:String},type:{type:String}},data:function(){return{pass:"true",comment:"同意"}},methods:{close:function(){this.$emit("update:open",!1)},complete:function(){var e=this;this.$parent.$children[0].validate((function(t){if(t){var a={taskId:e.taskId,instanceId:e.row.instanceId,variables:JSON.stringify({comment:e.comment,pass:e.pass,formData:e.row})};return Object(o["a"])({url:"/activiti/process/complete",method:"post",params:a}).then((function(t){e.close(),e.$parent.$parent.$parent.$parent.$parent.$parent.getList(),e.msgSuccess("操作成功")}))}}))}},watch:{pass:function(e){this.comment="true"===e?"同意":"驳回"}}},n=l,i=a("2877"),m=Object(i["a"])(n,r,s,!1,null,"4388be33",null);t["a"]=m.exports},"3bc1":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"请假类型",prop:"type"}},[a("el-select",{attrs:{disabled:"调整申请"!==e.taskName},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.typeOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1),a("el-form-item",{attrs:{label:"标题",prop:"title"}},[a("el-input",{attrs:{readonly:"调整申请"!==e.taskName},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),a("el-form-item",{attrs:{label:"原因",prop:"reason"}},[a("el-input",{attrs:{type:"textarea",readonly:"调整申请"!==e.taskName},model:{value:e.form.reason,callback:function(t){e.$set(e.form,"reason",t)},expression:"form.reason"}})],1),a("el-form-item",{attrs:{label:"开始时间",prop:"leaveStartTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:"调整申请"!==e.taskName},model:{value:e.form.leaveStartTime,callback:function(t){e.$set(e.form,"leaveStartTime",t)},expression:"form.leaveStartTime"}})],1),a("el-form-item",{attrs:{label:"结束时间",prop:"leaveEndTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:"调整申请"!==e.taskName},model:{value:e.form.leaveEndTime,callback:function(t){e.$set(e.form,"leaveEndTime",t)},expression:"form.leaveEndTime"}})],1),"销假"===e.taskName||"todo"!==e.type?a("el-form-item",{attrs:{label:"实际开始时间",prop:"realityStartTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择实际开始时间",readonly:"销假"!==e.taskName},model:{value:e.form.realityStartTime,callback:function(t){e.$set(e.form,"realityStartTime",t)},expression:"form.realityStartTime"}})],1):e._e(),"销假"===e.taskName||"todo"!==e.type?a("el-form-item",{attrs:{label:"实际结束时间",prop:"realityEndTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择实际结束时间",readonly:"销假"!==e.taskName},model:{value:e.form.realityEndTime,callback:function(t){e.$set(e.form,"realityEndTime",t)},expression:"form.realityEndTime"}})],1):e._e(),a("el-form-item",{attrs:{label:"申请人",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.applyUserName,callback:function(t){e.$set(e.form,"applyUserName",t)},expression:"form.applyUserName"}})],1),a("el-form-item",{attrs:{label:"申请时间",prop:"applyTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:""},model:{value:e.form.applyTime,callback:function(t){e.$set(e.form,"applyTime",t)},expression:"form.applyTime"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{type:"textarea",readonly:"调整申请"!==e.taskName},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1)],1),a("view-verify",{attrs:{open:e.open,row:e.row,taskId:e.taskId,type:e.type},on:{"update:open":function(t){e.open=t}}})],1)],1)},s=[],o=a("3657"),l={name:"LeaveForm",components:{ViewVerify:o["a"]},props:{row:{type:Object},taskId:{type:String},taskName:{type:String},type:{type:String,default:"none"}},data:function(){return{title:"查看请假表单",form:this.row,open:!1,typeOptions:[],rules:{type:[{required:!0,message:"请假类型不能为空",trigger:"blur"}],title:[{required:!0,message:"标题不能为空",trigger:"blur"}],reason:[{required:!0,message:"原因不能为空",trigger:"blur"}],leaveStartTime:[{required:!0,message:"开始时间不能为空",trigger:"blur"}],leaveEndTime:[{required:!0,message:"结束时间不能为空",trigger:"blur"}],realityStartTime:[{required:!0,message:"实际开始时间不能为空",trigger:"blur"}],realityEndTime:[{required:!0,message:"实际结束时间不能为空",trigger:"blur"}]}}},created:function(){var e=this;this.getDicts("biz_leave_type").then((function(t){e.typeOptions=t.data}))}},n=l,i=a("2877"),m=Object(i["a"])(n,r,s,!1,null,"775cfa2e",null);t["default"]=m.exports},"67fc":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"申请编号",prop:"applyNum"}},[a("el-input",{attrs:{readonly:"调整申请"!==e.taskName},model:{value:e.form.applyNum,callback:function(t){e.$set(e.form,"applyNum",t)},expression:"form.applyNum"}})],1),a("el-form-item",{attrs:{label:"是否高压停电"}},[a("el-radio-group",{attrs:{disabled:"调整申请"!==e.taskName},model:{value:e.form.highVoltage,callback:function(t){e.$set(e.form,"highVoltage",t)},expression:"form.highVoltage"}},[a("el-radio",{attrs:{label:"0"}},[e._v("否")]),a("el-radio",{attrs:{label:"1"}},[e._v("是")])],1)],1),a("el-form-item",{attrs:{label:"申请人",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:"调整申请"!==e.taskName},model:{value:e.form.applyUserName,callback:function(t){e.$set(e.form,"applyUserName",t)},expression:"form.applyUserName"}})],1),a("el-form-item",{attrs:{label:"申请时间",prop:"applyTime"}},[a("el-date-picker",{staticStyle:{width:"200px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择申请时间",readonly:"调整申请"!==e.taskName},model:{value:e.form.applyTime,callback:function(t){e.$set(e.form,"applyTime",t)},expression:"form.applyTime"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{type:"textarea",readonly:"调整申请"!==e.taskName},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1)],1),a("view-verify",{attrs:{open:e.open,row:e.row,taskId:e.taskId,type:e.type},on:{"update:open":function(t){e.open=t}}})],1)],1)},s=[],o=a("3657"),l={name:"DemoForm",components:{ViewVerify:o["a"]},props:{row:{type:Object},taskId:{type:String},taskName:{type:String},type:{type:String,default:"none"}},data:function(){return{title:"查看示例Demo",form:this.row,open:!1}}},n=l,i=a("2877"),m=Object(i["a"])(n,r,s,!1,null,"2a9d4643",null);t["default"]=m.exports},"977a":function(e,t,a){"use strict";a.d(t,"a",(function(){return r})),a.d(t,"b",(function(){return s}));a("ac1f"),a("5319");function r(e,t){var a=new Date(e),r=function(e){return(e<10?"0":"")+e};return t.replace(/yyyy|MM|dd|HH|mm|ss/g,(function(e){switch(e){case"yyyy":return r(a.getFullYear());case"MM":return r(a.getMonth()+1);case"mm":return r(a.getMinutes());case"dd":return r(a.getDate());case"HH":return r(a.getHours());case"ss":return r(a.getSeconds())}}))}function s(e){var t=Math.floor(e/86400),a=e%86400,r=Math.floor(a/3600),s=a%3600,o=Math.floor(s/60),l=s%60,n=Math.round(l);return t+"天"+r+"时"+o+"分"+n+"秒"}},aac6b:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-dialog",{attrs:{visible:e.open,title:"需求办理表单"},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{attrs:{model:e.form,inline:!0}},[a("el-form-item",{attrs:{label:"需求编号"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.demandId,callback:function(t){e.$set(e.form,"demandId",t)},expression:"form.demandId"}})],1),a("el-form-item",{attrs:{label:"需求名称"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.demandName,callback:function(t){e.$set(e.form,"demandName",t)},expression:"form.demandName"}})],1),a("el-form-item",{attrs:{label:"需求描述"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.demandDes,callback:function(t){e.$set(e.form,"demandDes",t)},expression:"form.demandDes"}})],1),a("el-form-item",{attrs:{label:"需求状态"}},[a("el-select",{attrs:{readonly:""},model:{value:e.form.demandState,callback:function(t){e.$set(e.form,"demandState",t)},expression:"form.demandState"}},e._l(e.demandStateOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1),a("span",[e._v(e._s(e.form))])],1)],1)],1)},s=[],o={name:"demandForm",data:function(){return{open:!1,form:{},demandStateOptions:[]}},created:function(){var e=this;this.getDicts("aesb_demand_state").then((function(t){e.demandStateOptions=t.data}))},methods:{}},l=o,n=(a("1d85"),a("2877")),i=Object(n["a"])(l,r,s,!1,null,"4b7d73f9",null);t["default"]=i.exports},c0c7:function(e,t,a){"use strict";a.d(t,"h",(function(){return o})),a.d(t,"e",(function(){return l})),a.d(t,"a",(function(){return n})),a.d(t,"j",(function(){return i})),a.d(t,"c",(function(){return m})),a.d(t,"d",(function(){return c})),a.d(t,"i",(function(){return u})),a.d(t,"b",(function(){return p})),a.d(t,"f",(function(){return d})),a.d(t,"k",(function(){return f})),a.d(t,"l",(function(){return y})),a.d(t,"m",(function(){return h})),a.d(t,"g",(function(){return b}));var r=a("b775"),s=a("c38a");function o(e){return Object(r["a"])({url:"/system/user/list",method:"get",params:e})}function l(e){return Object(r["a"])({url:"/system/user/"+Object(s["e"])(e),method:"get"})}function n(e){return Object(r["a"])({url:"/system/user",method:"post",data:e})}function i(e){return Object(r["a"])({url:"/system/user",method:"put",data:e})}function m(e){return Object(r["a"])({url:"/system/user/"+e,method:"delete"})}function c(e){return Object(r["a"])({url:"/system/user/export",method:"get",params:e})}function u(e,t){var a={userId:e,password:t};return Object(r["a"])({url:"/system/user/resetPwd",method:"put",data:a})}function p(e,t){var a={userId:e,status:t};return Object(r["a"])({url:"/system/user/changeStatus",method:"put",data:a})}function d(){return Object(r["a"])({url:"/system/user/profile",method:"get"})}function f(e){return Object(r["a"])({url:"/system/user/profile",method:"put",data:e})}function y(e,t){var a={oldPassword:e,newPassword:t};return Object(r["a"])({url:"/system/user/profile/updatePwd",method:"put",params:a})}function h(e){return Object(r["a"])({url:"/system/user/profile/avatar",method:"post",data:e})}function b(){return Object(r["a"])({url:"/system/user/importTemplate",method:"get"})}},d259:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-button",{staticStyle:{color:"#36a4da"},attrs:{size:"mini",type:"text",icon:e.icon},on:{click:e._handleView}},[e._v(e._s("todo"===e.type?"办理":"表单数据")+" ")]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"todo"===e.type,expression:"type === 'todo'"}],staticStyle:{color:"#36a4da"},attrs:{size:"mini",type:"text",icon:"el-icon-user"},on:{click:e.handleViewUserList}},[e._v("转办 ")]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"-2"!==e.row.taskId,expression:"row.taskId !== '-2'"}],staticStyle:{color:"#36a4da"},attrs:{size:"mini",type:"text",icon:"el-icon-time"},on:{click:e.handleViewHistoryList}},[e._v("审批历史 ")]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"-2"!==e.row.taskId,expression:"row.taskId !== '-2'"}],staticStyle:{color:"#36a4da"},attrs:{size:"mini",type:"text",icon:"el-icon-search"},on:{click:e.handleViewProcessImg}},[e._v("进度查看 ")]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"-1"!==e.row.taskId&&"-2"!==e.row.taskId&&"none"===e.type,expression:"row.taskId !== '-1' && row.taskId !== '-2' && type === 'none'"}],staticStyle:{color:"#ff2c50"},attrs:{size:"mini",type:"text",icon:"el-icon-back"},on:{click:e.handleCancel}},[e._v("撤销 ")]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"-1"!==e.row.taskId&&"-2"!==e.row.taskId&&"none"===e.type,expression:"row.taskId !== '-1' && row.taskId !== '-2' && type === 'none'"}],style:"2"===e.row.suspendState?"color: #13ce66":"color: #ff2c50",attrs:{size:"mini",type:"text",icon:"el-icon-sort"},on:{click:e.handleState}},[e._v(e._s("2"===e.row.suspendState?"激活":"挂起")+" ")]),"exampleDemo"===e.row.processKey?a("demo-form",{ref:"demoForm",attrs:{row:e.row,taskId:e.taskId,taskName:e.taskName,type:e.type}}):e._e(),"leave"===e.row.processKey?a("leave-form",{ref:"leaveForm",attrs:{row:e.row,taskId:e.taskId,taskName:e.taskName,type:e.type}}):e._e(),"process"===e.row.processKey?a("simple-form",{ref:"simpleForm",attrs:{row:e.row,taskId:e.taskId,taskName:e.taskName,type:e.type}}):e._e(),"sceneRelease"===e.row.processKey?a("audit-form",{ref:"auditForm",attrs:{row:e.row,taskId:e.taskId,taskName:e.taskName,type:e.type}}):e._e(),"demand"===e.row.processKey?a("demand-form",{ref:"demandForm",attrs:{row:e.row,taskId:e.taskId,taskName:e.taskName,type:e.type}}):e._e(),a("el-dialog",{attrs:{title:"进度查看",visible:e.open,width:"1000px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("div",{staticStyle:{width:"100%","text-align":"center"}},[a("el-image",{attrs:{src:e.src,"preview-src-list":e.srcList}})],1),a("div",{staticClass:"dialog-footer",staticStyle:{"text-align":"right"},attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.close}},[e._v("关闭")])],1)]),a("el-dialog",{attrs:{title:"审批历史",visible:e.showHistoryTable,width:"1000px","append-to-body":""},on:{"update:visible":function(t){e.showHistoryTable=t}}},[a("div",[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-width":"70px"}},[a("el-form-item",{attrs:{label:"任务名称",prop:"activityName"}},[a("el-input",{attrs:{placeholder:"请输入任务名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.activityName,callback:function(t){e.$set(e.queryParams,"activityName",t)},expression:"queryParams.activityName"}})],1),a("el-form-item",{attrs:{label:"办理人ID",prop:"assignee"}},[a("el-input",{attrs:{placeholder:"请输入办理人ID",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.assignee,callback:function(t){e.$set(e.queryParams,"assignee",t)},expression:"queryParams.assignee"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"cyan",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getHistoryList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.historyList}},[e._e(),a("el-table-column",{attrs:{label:"活动名称",align:"center",prop:"activityName",width:"140"}}),a("el-table-column",{attrs:{label:"办理人ID",align:"center",prop:"assignee",width:"90"}}),a("el-table-column",{attrs:{label:"办理人",align:"center",prop:"assigneeName",width:"80"}}),a("el-table-column",{attrs:{label:"审批意见",align:"center",prop:"comment"}}),a("el-table-column",{attrs:{label:"开始时间",align:"center",prop:"startTime",width:"160"}}),a("el-table-column",{attrs:{label:"结束时间",align:"center",prop:"endTime",width:"160"}}),a("el-table-column",{attrs:{label:"耗时",align:"center",prop:"durationInMillis",width:"150"}})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getHistoryList}})],1),a("div",{staticClass:"dialog-footer",staticStyle:{"text-align":"right"},attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.showHistoryTable=!1}}},[e._v("关闭")])],1)]),a("el-dialog",{attrs:{title:"选择用户",visible:e.showUserTable,width:"900px","append-to-body":""},on:{"update:visible":function(t){e.showUserTable=t}}},[a("div",[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showUserSearch,expression:"showUserSearch"}],ref:"queryUserForm",attrs:{model:e.queryUserParams,inline:!0,"label-width":"68px"}},[a("el-form-item",{attrs:{label:"用户名称",prop:"userName"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入用户名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleUserQuery(t)}},model:{value:e.queryUserParams.userName,callback:function(t){e.$set(e.queryUserParams,"userName",t)},expression:"queryUserParams.userName"}})],1),a("el-form-item",{attrs:{label:"手机号码",prop:"phonenumber"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入手机号码",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleUserQuery(t)}},model:{value:e.queryUserParams.phonenumber,callback:function(t){e.$set(e.queryUserParams,"phonenumber",t)},expression:"queryUserParams.phonenumber"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"cyan",icon:"el-icon-search",size:"mini"},on:{click:e.handleUserQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetUserQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("right-toolbar",{attrs:{showSearch:e.showUserSearch},on:{"update:showSearch":function(t){e.showUserSearch=t},"update:show-search":function(t){e.showUserSearch=t},queryTable:e.getUserList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.userLoading,expression:"userLoading"}],attrs:{data:e.userList},on:{"selection-change":e.handleUserSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"50",align:"center"}}),a("el-table-column",{attrs:{label:"用户编号",align:"center",prop:"userId"}}),a("el-table-column",{attrs:{label:"用户名称",align:"center",prop:"userName","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"用户昵称",align:"center",prop:"nickName","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"部门",align:"center",prop:"dept.deptName","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"手机号码",align:"center",prop:"phonenumber",width:"120"}}),a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.userTotal>0,expression:"userTotal>0"}],attrs:{total:e.userTotal,page:e.queryUserParams.pageNum,limit:e.queryUserParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryUserParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryUserParams,"pageSize",t)},pagination:e.getUserList}})],1),a("div",{staticClass:"dialog-footer",staticStyle:{"text-align":"right"},attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),a("el-button",{on:{click:function(t){e.showUserTable=!1}}},[e._v("关闭")])],1)])],1)},s=[],o=(a("d3b7"),a("159b"),a("b0c0"),a("b775")),l=a("977a"),n=a("c0c7"),i=a("67fc"),m=a("3bc1"),c=a("134f"),u=a("faef"),p=a("aac6b"),d={name:"ApplyAfter",components:{DemoForm:i["default"],LeaveForm:m["default"],simpleForm:c["default"],auditForm:u["default"],demandForm:p["default"]},props:{row:{type:Object},taskId:{type:String},taskName:{type:String},type:{type:String,default:"none"}},data:function(){return{open:!1,src:"",srcList:[],showHistoryTable:!1,loading:!1,queryParams:{pageNum:1,pageSize:999,processInstanceId:null,activityName:null,assignee:null},showSearch:!0,total:0,historyList:[],icon:"todo"===this.type?"el-icon-check":"el-icon-document",selectedRow:null,statusOptions:[],showUserTable:!1,userLoading:!1,queryUserParams:{pageNum:1,pageSize:10,userName:void 0,phonenumber:void 0,status:void 0},showUserSearch:!0,userTotal:0,userList:[]}},create:function(){var e=this;this.getDicts("sys_normal_disable").then((function(t){e.statusOptions=t.data}))},methods:{_handleView:function(){switch(this.row.processKey){case"exampleDemo":this.$refs.demoForm.form=this.row,this.$refs.demoForm.open=!0;break;case"leave":this.$refs.leaveForm.form=this.row,this.$refs.leaveForm.open=!0;break;case"process":this.$refs.simpleForm.form=this.row,this.$refs.simpleForm.open=!0;break;case"sceneRelease":this.$refs.auditForm.form=this.row,this.$refs.auditForm.open=!0;case"demand":this.$refs.demandForm.form=this.row,this.$refs.demandForm.open=!0;default:break}},handleState:function(){var e=this,t=this.row.instanceId,a=this.row.suspendState,r="2"===this.row.suspendState?"激活":"挂起";this.$confirm("是否确认"+r+'ID为"'+t+'"的流程实例?',"警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var e={instanceId:t,suspendState:a};return Object(o["a"])({url:"/activiti/process/suspendOrActiveApply",method:"post",params:e})})).then((function(t){e.$emit("getList"),e.msgSuccess("操作成功")})).catch((function(){}))},handleCancel:function(){var e=this,t=this.row.instanceId;this.$confirm('是否确认撤销ID为"'+t+'"的流程实例?',"警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var e={instanceId:t};return Object(o["a"])({url:"/activiti/process/cancelApply",method:"post",params:e})})).then((function(t){e.$emit("getList"),e.msgSuccess("操作成功")})).catch((function(){}))},handleViewProcessImg:function(){var e="/fwzcdt-admin";this.src=e+"/activiti/process/read-resource?pProcessInstanceId="+this.row.instanceId+"&_="+(new Date).getTime(),this.srcList=[],this.srcList.push(this.src),this.open=!0},close:function(){this.open=!1},handleViewHistoryList:function(){this.queryParams.processInstanceId=this.row.instanceId,this.showHistoryTable=!0,this.getHistoryList()},getHistoryList:function(){var e=this;return this.loading=!0,Object(o["a"])({url:"/activiti/process/listHistory",method:"post",data:this.queryParams}).then((function(t){e.historyList=t.rows,e.historyList.forEach((function(e){e.startTime=Object(l["a"])(e.startTime,"yyyy-MM-dd HH:mm:ss"),e.endTime=Object(l["a"])(e.endTime,"yyyy-MM-dd HH:mm:ss"),e.durationInMillis=Object(l["b"])(e.durationInMillis/1e3)})),e.total=t.total,e.loading=!1})).then((function(){}))},handleQuery:function(){this.queryParams.pageNum=1,this.getHistoryList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleViewUserList:function(){this.showUserTable=!0,this.getUserList()},getUserList:function(){var e=this;this.loading=!0,Object(n["h"])(this.queryUserParams).then((function(t){e.userList=t.rows,e.userTotal=t.total,e.userLoading=!1}))},handleUserQuery:function(){this.queryUserParams.page=1,this.getUserList()},resetUserQuery:function(){this.resetForm("queryUserForm"),this.handleUserQuery()},handleUserSelectionChange:function(e){this.selectedRow=e[0]},submitForm:function(){var e=this,t=this.taskId,a=this.selectedRow;a?a.userName!==this.$store.state.user.name?this.$confirm("是否确认转办给"+a.nickName+"?","警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return Object(o["a"])({url:"/activiti/process/delegate",method:"post",params:{taskId:t,delegateToUser:a.userName}})})).then((function(){e.$emit("getList"),e.msgSuccess("操作成功"),e.showUserTable=!1})):this.msgError("不能转办给自己"):this.msgError("请先选择要转办的用户")}}},f=d,y=a("2877"),h=Object(y["a"])(f,r,s,!1,null,"299ff3ee",null);t["a"]=h.exports},faef:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.title,visible:e.open,width:"500px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"服务场景码：",prop:"svCode"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.svCode,callback:function(t){e.$set(e.form,"svCode",t)},expression:"form.svCode"}})],1),a("el-form-item",{attrs:{label:"服务场景名称：",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.svName,callback:function(t){e.$set(e.form,"svName",t)},expression:"form.svName"}})],1),a("el-form-item",{attrs:{label:"申请人ID",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.applyUserId,callback:function(t){e.$set(e.form,"applyUserId",t)},expression:"form.applyUserId"}})],1),a("el-form-item",{attrs:{label:"申请人",prop:"applyUserName"}},[a("el-input",{attrs:{readonly:""},model:{value:e.form.applyUserName,callback:function(t){e.$set(e.form,"applyUserName",t)},expression:"form.applyUserName"}})],1),a("el-form-item",{attrs:{label:"申请时间",prop:"applyTime"}},[a("el-date-picker",{staticStyle:{width:"360px"},attrs:{clearable:"",size:"small",type:"date","value-format":"yyyy-MM-dd",readonly:""},model:{value:e.form.applyTime,callback:function(t){e.$set(e.form,"applyTime",t)},expression:"form.applyTime"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"updateReason"}},[a("el-input",{attrs:{type:"textarea",readonly:!0},model:{value:e.form.updateReason,callback:function(t){e.$set(e.form,"updateReason",t)},expression:"form.updateReason"}})],1)],1),a("view-verify",{attrs:{open:e.open,row:e.row,taskId:e.taskId,type:e.type},on:{"update:open":function(t){e.open=t}}})],1)],1)},s=[],o=a("3657"),l={name:"Audit",components:{ViewVerify:o["a"]},props:{row:{type:Object},taskId:{type:String},taskName:{type:String},type:{type:String,default:"none"}},data:function(){return{title:"",open:!1,form:this.row,rules:{realityStartTime:[{required:!0,message:"实际开始时间不能为空",trigger:["change","blur"]}],realityEndTime:[{required:!0,message:"实际结束时间不能为空",trigger:["change","blur"]}]}}}},n=l,i=a("2877"),m=Object(i["a"])(n,r,s,!1,null,null,null);t["default"]=m.exports}}]);