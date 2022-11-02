<template>
    <div class="app-container">
        <el-button @click="imports">上传</el-button>
        <el-dialog width="30%" :visible.sync="imports_show">
            <el-upload
                ref="upload"
                class="upload-demo"
                action="#"
                accept="xlsx,xls"
                multiple
                :limit="1"
                :auto-upload="false"
                :withCredentials="false"
                :on-change="loadFile"
            >
                <el-button slot="trigger" size="mini" type="success" plain round>选择文件</el-button>
                <el-button style="margin-left" size="mini" type="warning" plain round @click="batImport()">数据上传</el-button>
                <br></br>
                <div slot="tip" class="el-upload__tip">【注意】只能上传xls/xlsx文件</div>
            </el-upload>
        </el-dialog>

    </div>
</template>

<script>
    import XLSX from 'xlsx'
    import {getToken} from "@/utils/auth";

    export default {
        name: 'REQUEST',
        data() {
            return {
                imports_show: false,
                upload_file: '',
                excelData: [],
            }
        },
        methods: {
            imports() {
                this.imports_show = !this.imports_show;
            },
            loadFile(file, fileList) {
                this.upload_file = fileList[0].raw;
                const reader = new FileReader();
                //前三sheet表
                const formData = [];
                //场景字段
                const lastData = [];
                reader.readAsBinaryString(this.upload_file);
                reader.onload = ev => {
                    try {
                        const f = ev.target.result;
                        const workbook = XLSX.read(f, {type: "binary"}, {header:0});
                        //前三sheet表
                        for (let i = 0; i < 3; i++) {
                            formData.push(XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[i]],{range:1}))
                        }
                        lastData.push(XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[3]],{range:1}))
                        //服务分类
                        const serviceData = formData[0].map((item)=>{
                            return {
                                serviceHeadClass: item['一级分类'],
                                serviceSmallClass: item['二级分类'],
                                svcCode: item['服务ID'],
                                svcName: item['服务名称']
                            }
                        })

                        //服务场景
                        const svSceneData = formData[1].map((item)=>{
                            return {
                                svCommunicationProtocol: item['通讯协议'],
                                svCode: item['服务场景码'],
                                svName: item['服务场景名称'],
                                svInterface: item['接口交易码'],
                                svMessageFormat: item['报文格式'],
                                svType: item['场景类型'],
                                updateReason: item['变更原因'],
                                updateContent: item['变更内容'],
                                sysName: item['交易提供者'],
                                svHttpUrl: item['HTTP-URL'],

                                svcCode: item['服务ID'],
                                svcName: item['服务名称']
                            }
                        })
                        //场景指标
                        const svParamsData = formData[2].map((item)=>{
                            return {
                                svCode: item['服务场景码'],
                                svName: item['服务场景名称'],
                                sceneTimeout: item['服务超时时间']
                            }
                        })
                        //场景字段
                        let num = 0;
                        for (let i = 0; i < lastData[0].length; i++) {
                            if (lastData[0][i].英文名称==='服务场景字段（输出）'){
                                num = i+2;
                                break;
                            }
                        }
                        //输入
                        const inData=[]
                        //输出
                        const outData=[]
                        for (let i = 0; i < num-2; i++) {
                            inData.push(lastData[0][i])
                        }
                        for (let i = num; i < lastData[0].length; i++) {
                            outData.push(lastData[0][i])
                        }

                        var newInData = inData.map(item => ({
                            sfEnName: item['英文名称'],
                            sfCnName: item['中文名称'],
                            sfDataType: item['数据类型'],
                            sfPath: item['字段位置'],
                            sfLength: item['字段长度（BYTE）'],
                            sfFormat: item['数据格式'],
                            sfIsEnssential: item['是否必输'],
                            sfIsSensitive: item['是否敏感字段'],
                            sfLengthType: item['长度类型']
                        }))
                        var newOutData = outData.map(item => ({
                            sfEnName: item['英文名称'],
                            sfCnName: item['中文名称'],
                            sfDataType: item['数据类型'],
                            sfPath: item['字段位置'],
                            sfLength: item['字段长度（BYTE）'],
                            sfFormat: item['数据格式'],
                            sfIsEnssential: item['是否必输'],
                            sfIsSensitive: item['是否敏感字段'],
                            sfLengthType: item['长度类型']
                        }))
                        console.log("newInData:",newInData)
                        console.log("newOutData:",newOutData)
                        console.log(serviceData)
                        console.log(svSceneData)
                        console.log(svParamsData)
                        //

                    } catch (e) {
                        console.log(e);
                        return false;
                    }
                };
            },
        }
    }
</script>
<style lang="less" scoped>
</style>
