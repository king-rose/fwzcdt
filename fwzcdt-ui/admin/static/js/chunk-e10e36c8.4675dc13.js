(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e10e36c8"],{"02a0":function(e,t,l){"use strict";l("ff23")},"22d3":function(e,t,l){"use strict";l.r(t);var a=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("el-radio-group",{staticStyle:{"margin-bottom":"30px"},model:{value:e.label,callback:function(t){e.label=t},expression:"label"}},[l("el-radio-button",{attrs:{label:"0"}},[e._v("场景基本信息")]),l("el-radio-button",{attrs:{label:"1"}},[e._v("场景指标")]),l("el-radio-button",{attrs:{label:"2"}},[e._v("场景字段")]),l("el-radio-button",{attrs:{label:"3"}},[e._v("变更详情")])],1),l("el-form",{ref:"form",attrs:{model:e.form_1,inline:!0,"label-width":"100px","label-suffix":":","label-position":"left",rules:e.rules}},[l("div",{directives:[{name:"show",rawName:"v-show",value:"0"===e.label,expression:"label==='0'"}]},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"所属项目",required:"",prop:"projectCode"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.projectCode,callback:function(t){e.$set(e.form_1,"projectCode",t)},expression:"form_1.projectCode"}},[l("el-option",{attrs:{label:"test",value:""}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"所属系统",required:"",prop:"scnSysId"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.scnSysId,callback:function(t){e.$set(e.form_1,"scnSysId",t)},expression:"form_1.scnSysId"}},e._l(e.systemOptions,(function(e){return l("el-option",{key:e.sysId,attrs:{label:e.sysName,value:e.sysId}})})),1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"接口",required:"",prop:"svInterface"}},[l("el-input",{staticStyle:{width:"120%"},model:{value:e.form_1.svInterface,callback:function(t){e.$set(e.form_1,"svInterface",t)},expression:"form_1.svInterface"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"所属服务",required:"",prop:"serviceId"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.serviceId,callback:function(t){e.$set(e.form_1,"serviceId",t)},expression:"form_1.serviceId"}},[l("el-option",{attrs:{label:"G01201002-项目管理",value:"0"}}),l("el-option",{attrs:{label:"ZT08-广播服务",value:"1"}})],1)],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"场景码",required:"",prop:"svCode"}},[l("el-input",{attrs:{placeholder:"请输入场景码"},model:{value:e.form_1.svCode,callback:function(t){e.$set(e.form_1,"svCode",t)},expression:"form_1.svCode"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"场景名称",required:"",prop:"svName"}},[l("el-input",{attrs:{placeholder:"请输入场景名称"},model:{value:e.form_1.svName,callback:function(t){e.$set(e.form_1,"svName",t)},expression:"form_1.svName"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"场景类型",required:"",prop:"svType"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.svType,callback:function(t){e.$set(e.form_1,"svType",t)},expression:"form_1.svType"}},[l("el-option",{attrs:{label:"服务场景",value:"1"}}),l("el-option",{attrs:{label:"主题场景",value:"2"}}),l("el-option",{attrs:{label:"组合服务场景",value:"3"}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"报文格式",required:"",prop:"svMessageFormat"}},[l("el-input",{staticStyle:{width:"120%"},model:{value:e.form_1.svMessageFormat,callback:function(t){e.$set(e.form_1,"svMessageFormat",t)},expression:"form_1.svMessageFormat"}})],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"通讯协议"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.svCommunicationProtocol,callback:function(t){e.$set(e.form_1,"svCommunicationProtocol",t)},expression:"form_1.svCommunicationProtocol"}},[l("el-option",{attrs:{label:"TCP",value:"TCP"}}),l("el-option",{attrs:{label:"HTTP",value:"HTTP"}}),l("el-option",{attrs:{label:"HTTPS",value:"HTTPS"}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"HTTP-URL"}},[l("el-input",{attrs:{placeholder:"请输入url"},model:{value:e.form_1.svHttpUrl,callback:function(t){e.$set(e.form_1,"svHttpUrl",t)},expression:"form_1.svHttpUrl"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"是否适配"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.jkAdapFlag,callback:function(t){e.$set(e.form_1,"jkAdapFlag",t)},expression:"form_1.jkAdapFlag"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"是否校验"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.xmlValidFlag,callback:function(t){e.$set(e.form_1,"xmlValidFlag",t)},expression:"form_1.xmlValidFlag"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"是否对标"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_1.stdFieldCheck,callback:function(t){e.$set(e.form_1,"stdFieldCheck",t)},expression:"form_1.stdFieldCheck"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"外部接口码"}},[l("el-input",{attrs:{placeholder:"请输入外部接口码"},model:{value:e.form_1.svExternalInterfaceCode,callback:function(t){e.$set(e.form_1,"svExternalInterfaceCode",t)},expression:"form_1.svExternalInterfaceCode"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"场景描述"}},[l("el-input",{attrs:{maxlength:"200","show-word-limit":"",type:"textarea",placeholder:"请输入场景描述"},model:{value:e.form_1.scnDes,callback:function(t){e.$set(e.form_1,"scnDes",t)},expression:"form_1.scnDes"}})],1)],1),l("el-col",{attrs:{span:6}})],1)],1),l("div",{directives:[{name:"show",rawName:"v-show",value:"3"===e.label,expression:"label==='3'"}]},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"center"}},[l("el-col",{attrs:{span:8}},[l("el-form-item",{staticClass:"code",attrs:{label:"变更内容",required:""}},[l("el-input",{attrs:{type:"textarea",placeholder:"请输入变更内容"},model:{value:e.form_1.updateContent,callback:function(t){e.$set(e.form_1,"updateContent",t)},expression:"form_1.updateContent"}})],1)],1),l("el-col",{attrs:{span:8}},[l("el-form-item",{staticClass:"code",attrs:{label:"变更原因",required:""}},[l("el-input",{attrs:{type:"textarea",placeholder:"请输入变更原因"},model:{value:e.form_1.updateReason,callback:function(t){e.$set(e.form_1,"updateReason",t)},expression:"form_1.updateReason"}})],1)],1)],1),l("el-row",{staticStyle:{float:"right"}},[l("el-button",{attrs:{type:"primary"}},[e._v("取消")]),l("el-button",{attrs:{type:"primary"},on:{click:e.submit}},[e._v("提交")])],1)],1)]),l("el-form",{ref:"form",attrs:{model:e.form_2,inline:!0,"label-width":"100px","label-suffix":":","label-position":"left",rules:e.rules}},[l("div",{directives:[{name:"show",rawName:"v-show",value:"1"===e.label,expression:"label==='1'"}]},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"服务窗口时间"}},[l("el-input",{attrs:{placeholder:"请输入服务窗口时间"},model:{value:e.form_2.sceneWindowTime,callback:function(t){e.$set(e.form_2,"sceneWindowTime",t)},expression:"form_2.sceneWindowTime"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"服务超时时间"}},[l("el-input",{attrs:{placeholder:"请输入服务超时时间"},model:{value:e.form_2.sceneTimeout,callback:function(t){e.$set(e.form_2,"sceneTimeout",t)},expression:"form_2.sceneTimeout"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"并发用户数"}},[l("el-input",{attrs:{placeholder:"请输入并发用户数"},model:{value:e.form_2.oneTimeUserNum,callback:function(t){e.$set(e.form_2,"oneTimeUserNum",t)},expression:"form_2.oneTimeUserNum"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"TPS每秒笔数"}},[l("el-input",{attrs:{placeholder:"请输入TPS每秒笔数"},model:{value:e.form_2.tradePerSecond,callback:function(t){e.$set(e.form_2,"tradePerSecond",t)},expression:"form_2.tradePerSecond"}})],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"服务场景生效时间"}},[l("el-input",{attrs:{placeholder:"请输入服务窗口时间"},model:{value:e.form_2.onlineDate,callback:function(t){e.$set(e.form_2,"onlineDate",t)},expression:"form_2.onlineDate"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"服务场景失效时间"}},[l("el-input",{attrs:{placeholder:"请输入服务超时时间"},model:{value:e.form_2.offlineDate,callback:function(t){e.$set(e.form_2,"offlineDate",t)},expression:"form_2.offlineDate"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"优先级"}},[l("el-input",{attrs:{placeholder:"请输入优先级"},model:{value:e.form_2.scenePriority,callback:function(t){e.$set(e.form_2,"scenePriority",t)},expression:"form_2.scenePriority"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"是否开放场景"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_2.isOpen,callback:function(t){e.$set(e.form_2,"isOpen",t)},expression:"form_2.isOpen"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"是否免费"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_2.freeFlag,callback:function(t){e.$set(e.form_2,"freeFlag",t)},expression:"form_2.freeFlag"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"方法名称"}},[l("el-input",{attrs:{placeholder:"请输入方法名称"},model:{value:e.form_2.methodName,callback:function(t){e.$set(e.form_2,"methodName",t)},expression:"form_2.methodName"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"请求链接"}},[l("el-input",{attrs:{placeholder:"请输入请求链接"},model:{value:e.form_2.requestUrl,callback:function(t){e.$set(e.form_2,"requestUrl",t)},expression:"form_2.requestUrl"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"请求单价"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_2.apiPrice,callback:function(t){e.$set(e.form_2,"apiPrice",t)},expression:"form_2.apiPrice"}},[l("el-option",{attrs:{label:"否",value:"0"}}),l("el-option",{attrs:{label:"是",value:"1"}})],1)],1)],1)],1),l("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"space-around"}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"API类型"}},[l("el-input",{attrs:{placeholder:"请输入API类型"},model:{value:e.form_2.apiType,callback:function(t){e.$set(e.form_2,"apiType",t)},expression:"form_2.apiType"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"API版本号"}},[l("el-input",{attrs:{placeholder:"请输入API版本号"},model:{value:e.form_2.apiVersion,callback:function(t){e.$set(e.form_2,"apiVersion",t)},expression:"form_2.apiVersion"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{staticClass:"code",attrs:{label:"用户授权标识"}},[l("el-input",{attrs:{placeholder:"请输入用户授权标识"},model:{value:e.form_2.authFlag,callback:function(t){e.$set(e.form_2,"authFlag",t)},expression:"form_2.authFlag"}})],1)],1),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"HTTP方法"}},[l("el-select",{staticStyle:{width:"110%"},model:{value:e.form_2.httpMethod,callback:function(t){e.$set(e.form_2,"httpMethod",t)},expression:"form_2.httpMethod"}},[l("el-option",{attrs:{label:"POST",value:"0"}}),l("el-option",{attrs:{label:"GET",value:"1"}})],1)],1)],1)],1)],1)]),l("div",{directives:[{name:"show",rawName:"v-show",value:"2"===e.label,expression:"label==='2'"}]},[l("el-card",{staticClass:"box-card items mb20"},[l("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[l("span",{staticClass:"title"},[e._v("场景输入字段")]),l("el-button",{staticClass:"addButton",attrs:{type:"primary",size:"small",plain:""},on:{click:function(t){return e.add("In")}}},[e._v("新增")])],1),l("div",{staticClass:"text item"},[l("el-table",{ref:"inTable",staticStyle:{"font-size":"14px"},attrs:{data:e.fieldInList,"row-style":{height:"20px"},"cell-style":{padding:"10px"},"highlight-current-row":""}},[l("el-table-column",{attrs:{label:"序号",align:"center","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.$index+1)+" ")]}}])}),l("el-table-column",{attrs:{label:"字段中文名",align:"center",prop:"sfCnName","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfCnName,callback:function(l){e.$set(t.row,"sfCnName",l)},expression:"scope.row.sfCnName"}})]}}])}),l("el-table-column",{attrs:{label:"字段英文名",align:"center",prop:"sfEnName","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfEnName,callback:function(l){e.$set(t.row,"sfEnName",l)},expression:"scope.row.sfEnName"}})]}}])}),l("el-table-column",{attrs:{label:"字段类型",align:"center",prop:"sfDataType","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:"1"===t.row.sfDataType?"String":"",callback:function(l){e.$set(t.row,"sfDataType==='1'? 'String':''",l)},expression:"scope.row.sfDataType==='1'? 'String':''"}})]}}])}),l("el-table-column",{attrs:{label:"字段长度",align:"center",prop:"sfLength","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input-number",{staticStyle:{width:"100px"},attrs:{size:"small",label:"字段长度",min:0},model:{value:t.row.sfLength,callback:function(l){e.$set(t.row,"sfLength",l)},expression:"scope.row.sfLength"}})]}}])}),l("el-table-column",{attrs:{label:"是否必输",align:"center",prop:"sfIsEnssential","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-select",{model:{value:t.row.sfIsEnssential,callback:function(l){e.$set(t.row,"sfIsEnssential",l)},expression:"scope.row.sfIsEnssential"}},[l("el-option",{attrs:{label:"非必输",value:"0"}}),l("el-option",{attrs:{label:"必输",value:"1"}})],1)]}}])}),l("el-table-column",{attrs:{label:"是否敏感",align:"center",prop:"sfIsSensitive","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-select",{model:{value:t.row.sfIsSensitive,callback:function(l){e.$set(t.row,"sfIsSensitive",l)},expression:"scope.row.sfIsSensitive"}},[l("el-option",{attrs:{label:"非敏感",value:"0"}}),l("el-option",{attrs:{label:"敏感",value:"1"}})],1)]}}])}),l("el-table-column",{attrs:{label:"默认值",align:"center",prop:"sfDefault","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfDefault,callback:function(l){e.$set(t.row,"sfDefault",l)},expression:"scope.row.sfDefault"}})]}}])}),l("el-table-column",{attrs:{label:"字段描述",align:"center",prop:"sfDes","min-width":"8%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{attrs:{type:"textarea"},model:{value:t.row.sfDes,callback:function(l){e.$set(t.row,"sfDes",l)},expression:"scope.row.sfDes"}})]}}])}),l("el-table-column",{attrs:{label:"枚举值",align:"center",prop:"sfEnum","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfEnum,callback:function(l){e.$set(t.row,"sfEnum",l)},expression:"scope.row.sfEnum"}})]}}])}),l("el-table-column",{attrs:{label:"循环次数",align:"center",prop:"sfCycleTimes","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfCycleTimes,callback:function(l){e.$set(t.row,"sfCycleTimes",l)},expression:"scope.row.sfCycleTimes"}})]}}])}),l("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(l){return e.deleteRow("in",t.$index)}}},[e._v("删除 ")])]}}])})],1),l("pagination",{directives:[{name:"show",rawName:"v-show",value:e.fieldInParams.total>0,expression:"fieldInParams.total>0"}],attrs:{total:e.fieldInParams.total,page:e.fieldInParams.pageNum,limit:e.fieldInParams.pageSize},on:{"update:page":function(t){return e.$set(e.fieldInParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.fieldInParams,"pageSize",t)},pagination:e.getFieldInList}})],1)]),l("el-card",{staticClass:"box-card items mb20"},[l("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[l("span",{staticClass:"title"},[e._v("场景输出字段")]),l("el-button",{staticClass:"addButton",attrs:{type:"primary",size:"small",plain:""},on:{click:function(t){return e.add("Out")}}},[e._v("新增")])],1),l("div",{staticClass:"text item"},[l("el-table",{ref:"inTable",staticStyle:{"font-size":"14px"},attrs:{data:e.fieldOutList,"row-style":{height:"20px"},"cell-style":{padding:"10px"},"highlight-current-row":"","row-class-name":e.tableRowClassName}},[l("el-table-column",{attrs:{label:"序号",align:"center","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.$index+1)+" ")]}}])}),l("el-table-column",{attrs:{label:"字段中文名",align:"center",prop:"sfCnName","min-width":"8%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfCnName,callback:function(l){e.$set(t.row,"sfCnName",l)},expression:"scope.row.sfCnName"}})]}}])}),l("el-table-column",{attrs:{label:"字段英文名",align:"center",prop:"sfEnName","min-width":"8%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfEnName,callback:function(l){e.$set(t.row,"sfEnName",l)},expression:"scope.row.sfEnName"}})]}}])}),l("el-table-column",{attrs:{label:"数据类型",align:"center",prop:"sfDataType","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:"1"===t.row.sfDataType?"String":"",callback:function(l){e.$set(t.row,"sfDataType==='1'? 'String':''",l)},expression:"scope.row.sfDataType==='1'? 'String':''"}})]}}])}),l("el-table-column",{attrs:{label:"字段长度",align:"center",prop:"sfLength","min-width":"7%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input-number",{staticStyle:{width:"100px"},attrs:{size:"small",label:"字段长度",min:0},model:{value:t.row.sfLength,callback:function(l){e.$set(t.row,"sfLength",l)},expression:"scope.row.sfLength"}})]}}])}),l("el-table-column",{attrs:{label:"小数位",align:"center",prop:"sfFraction","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfFraction,callback:function(l){e.$set(t.row,"sfFraction",l)},expression:"scope.row.sfFraction"}})]}}])}),l("el-table-column",{attrs:{label:"默认值",align:"center",prop:"sfDefault","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfDefault,callback:function(l){e.$set(t.row,"sfDefault",l)},expression:"scope.row.sfDefault"}})]}}])}),l("el-table-column",{attrs:{label:"是否必输",align:"center",prop:"sfIsEnssential","min-width":"6%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-select",{model:{value:t.row.sfIsEnssential,callback:function(l){e.$set(t.row,"sfIsEnssential",l)},expression:"scope.row.sfIsEnssential"}},[l("el-option",{attrs:{label:"非必输",value:"0"}}),l("el-option",{attrs:{label:"必输",value:"1"}})],1)]}}])}),l("el-table-column",{attrs:{label:"枚举列表",align:"center",prop:"sfEnum","min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfEnum,callback:function(l){e.$set(t.row,"sfEnum",l)},expression:"scope.row.sfEnum"}})]}}])}),l("el-table-column",{attrs:{label:"字段位置",align:"center",prop:"sfPath","min-width":"10%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfPath,callback:function(l){e.$set(t.row,"sfPath",l)},expression:"scope.row.sfPath"}})]}}])}),l("el-table-column",{attrs:{label:"备注",align:"center",prop:"sfDes","min-width":"10%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-input",{model:{value:t.row.sfDes,callback:function(l){e.$set(t.row,"sfDes",l)},expression:"scope.row.sfDes"}})]}}])}),l("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width","min-width":"10%"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(l){return e.deleteRow("out",t.$index)}}},[e._v("删除 ")])]}}])})],1),l("pagination",{directives:[{name:"show",rawName:"v-show",value:e.fieldOutParams.total>0,expression:"fieldOutParams.total>0"}],attrs:{total:e.fieldOutParams.total,page:e.fieldOutParams.pageNum,limit:e.fieldOutParams.pageSize},on:{"update:page":function(t){return e.$set(e.fieldOutParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.fieldOutParams,"pageSize",t)},pagination:e.getFieldOutList}})],1)])],1)],1)},s=[],n=(l("a434"),l("e9c4"),l("99af"),l("43d3")),o=l("8136"),r=l("b775"),i={name:"serviceAdd",components:{},data:function(){return{svId:"",svCode:"",label:"0",inLoading:!1,outLoading:!1,fieldInParams:{pageNum:1,pageSize:10,total:0,svId:"",type:"0"},fieldOutParams:{pageNum:1,pageSize:10,total:0,svId:"",type:"1"},form_1:{},form_2:{},rules:{projectCode:[{required:!0,message:"项目不能为空",trigger:"blur"}],scnSysId:[{required:!0,message:"系统不能为空",trigger:"blur"}],svInterface:[{required:!0,message:"接口不能为空",trigger:"blur"}],serviceId:[{required:!0,message:"所属服务不能为空",trigger:"blur"}],svCode:[{required:!0,message:"场景码不能为空",trigger:"blur"}],svName:[{required:!0,message:"场景名称不能为空",trigger:"blur"}],svType:[{required:!0,message:"场景类型不能为空",trigger:"blur"}],svMessageFormat:[{required:!0,message:"报文格式不能为空",trigger:"blur"}]},fieldInList:[],fieldOutList:[],systemOptions:[],tempRoute:{},open:!1}},created:function(){this.svId=this.$route.query.svId,this.getSystemList(),this.getSceneInfo()},activated:function(){this.tempRoute=Object.assign({},this.$route),this.setTagsViewTitle()},methods:{reset:function(){this.form_1={},this.form_2={},this.label="0"},setTagsViewTitle:function(){var e="服务场景编辑",t=Object.assign({},this.tempRoute,{title:"".concat(e)});this.$store.dispatch("tagsView/updateVisitedView",t)},add:function(e){if("In"===e){void 0==this.fieldInList&&(this.fieldInList=new Array);var t={};t.scnCode=this.svCode,t.sfCnName="",t.sfEnName="",t.sfType="0",t.sfDataType="1",t.sfLength="0",this.fieldInList.push(t)}if("Out"===e){void 0==this.fieldOutList&&(this.fieldOutList=new Array);var l={};l.scnCode=this.svCode,l.sfCnName="",l.sfEnName="",l.sfType="1",l.sfDataType="1",l.sfLength="0",this.fieldOutList.push(l)}},deleteRow:function(e,t){var l=this,a=t+1;this.$confirm('是否删除第"'+a+'"行数据?',"警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(a){"in"===e&&l.fieldInList.splice(t,1),"out"===e&&l.fieldOutList.splice(t,1)}))},tableRowClassName:function(e){var t=e.row,l=e.rowIndex;t.index=l},getSystemList:function(){var e=this;Object(o["i"])().then((function(t){e.systemOptions=t.rows}))},getSceneInfo:function(){var e=this;this.reset(),Object(n["k"])(this.svId).then((function(t){e.form_1=t.data,e.svCode=t.data.svCode})),Object(n["h"])(this.svId).then((function(t){t.data&&(e.form_2=t.data)})),this.getFieldInList(),this.getFieldOutList()},getFieldInList:function(){var e=this;this.inLoading=!0,this.fieldInParams.svId=this.svId,Object(n["f"])(this.fieldInParams).then((function(t){e.fieldInList=t.rows,e.fieldInParams.total=t.total,e.inLoading=!1}))},getFieldOutList:function(){var e=this;this.outLoading=!0,this.fieldOutParams.svId=this.svId,Object(n["g"])(this.fieldOutParams).then((function(t){e.fieldOutList=t.rows,e.fieldOutParams.total=t.total,e.outLoading=!1}))},submit:function(){var e=this;this.$refs["form"].validate((function(t){t&&Object(n["l"])(e.form_1).then((function(e){console.log(e)})).then((function(){e.form_2.sceneVersionId=e.svId,Object(n["j"])(e.form_2).then((function(e){console.log(e)}))})).then((function(){var t=e.svId,l={formData:JSON.stringify(e.fieldInList.concat(e.fieldOutList))};e.$confirm('是否提交ID为"'+t+'"的申请单据?',"警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return Object(r["a"])({url:"oprScene/sceneFieldEdit/"+t,method:"get",params:l})})).then((function(){e.$emit("getList"),e.msgSuccess("更新成功"),e.$store.dispatch("tagsView/delView",e.$route).then((function(){e.$router.push({path:"/service/list"})}))})).catch((function(e){"cancel"!==e?this.msgError("更新失败!:"+e):this.msgWarning("已取消")}))}))}))}}},c=i,u=(l("02a0"),l("2877")),f=Object(u["a"])(c,a,s,!1,null,null,null);t["default"]=f.exports},"43d3":function(e,t,l){"use strict";l.d(t,"m",(function(){return s})),l.d(t,"a",(function(){return n})),l.d(t,"k",(function(){return o})),l.d(t,"i",(function(){return r})),l.d(t,"h",(function(){return i})),l.d(t,"f",(function(){return c})),l.d(t,"g",(function(){return u})),l.d(t,"c",(function(){return f})),l.d(t,"b",(function(){return m})),l.d(t,"l",(function(){return d})),l.d(t,"j",(function(){return p})),l.d(t,"d",(function(){return b})),l.d(t,"e",(function(){return h})),l.d(t,"n",(function(){return v}));var a=l("b775");function s(e){return Object(a["a"])({url:"/oprScene/list",method:"get",params:e})}function n(e){return Object(a["a"])({url:"/oprScene/remove/"+e,method:"delete"})}function o(e){return Object(a["a"])({url:"oprScene/basic/"+e,method:"get"})}function r(e){return Object(a["a"])({url:"oprScene/resource/"+e,method:"get"})}function i(e){return Object(a["a"])({url:"oprScene/quato/"+e,method:"get"})}function c(e){return Object(a["a"])({url:"oprScene/getSceneFiledIn",method:"get",params:e})}function u(e){return Object(a["a"])({url:"oprScene/getSceneFiledOut",method:"get",params:e})}function f(e){return Object(a["a"])({url:"oprScene/consume/"+e,method:"get"})}function m(e){return Object(a["a"])({url:"oprScene/change/"+e,method:"get"})}function d(e){return Object(a["a"])({url:"oprScene/edit",method:"post",params:e})}function p(e){return Object(a["a"])({url:"/oprScene/sceneQuatodEdit",method:"post",params:e})}function b(e){return Object(a["a"])({url:"oprScene/defined/in",method:"get",params:e})}function h(e){return Object(a["a"])({url:"oprScene/defined/out",method:"get",params:e})}function v(e){return Object(a["a"])({url:"oprScene/release",method:"post",data:e})}},8136:function(e,t,l){"use strict";l.d(t,"h",(function(){return s})),l.d(t,"d",(function(){return n})),l.d(t,"f",(function(){return o})),l.d(t,"i",(function(){return r})),l.d(t,"a",(function(){return i})),l.d(t,"g",(function(){return c})),l.d(t,"e",(function(){return u})),l.d(t,"c",(function(){return f})),l.d(t,"b",(function(){return m})),l.d(t,"j",(function(){return d})),l.d(t,"k",(function(){return p}));var a=l("b775");function s(){return Object(a["a"])({url:"/system/techDepts",method:"get"})}function n(){return Object(a["a"])({url:"/system/principals",method:"get"})}function o(){return Object(a["a"])({url:"/system/systemLayers",method:"get"})}function r(e){return Object(a["a"])({url:"/system/list",method:"get",params:e})}function i(e){return Object(a["a"])({url:"/system",method:"post",data:e})}function c(){return Object(a["a"])({url:"/system/sysNum",method:"get"})}function u(){return Object(a["a"])({url:"/system/sceneNum",method:"get"})}function f(){return Object(a["a"])({url:"/system/consumeNum",method:"get"})}function m(e){return Object(a["a"])({url:"/system/trend/"+e,method:"get"})}function d(){return Object(a["a"])({url:"/system/scene/ratio",method:"get"})}function p(){return Object(a["a"])({url:"/system/scene/serviceScene",method:"get"})}},ff23:function(e,t,l){}}]);