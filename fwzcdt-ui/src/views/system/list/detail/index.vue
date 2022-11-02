<template>
    <div class="app-container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="基本信息" name="one">
                <div style="width: 85%;margin: 0 auto;">
                    <el-collapse v-model="activeNames" @change="handleClick">
                        <el-collapse-item title="基本信息" name="1" class="yangShi">
                            <el-row>
                                <el-col :span="12">
                                    系统编号：ACS
                                </el-col>
                                <el-col :span="12">
                                    系统名称：中央银行会计核算数据集中系统
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    系统概述：中央银行会计核算数据集中系统
                                </el-col>
                                <el-col :span="12">
                                    系统所属层：渠道门户层
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    所属区域：宁夏银行总行
                                </el-col>
                                <el-col :span="12">
                                    开发厂商：无
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    系统上线日期：2022-03-02
                                </el-col>
                                <el-col :span="12">
                                    系统下线日期：2022-05-15
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    超时时间(500ms)：55000
                                </el-col>
                                <el-col :span="12">
                                    用途(字符集)：消费方(UTF-8)
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    渠道编码：无
                                </el-col>
                                <el-col :span="12">
                                    是否已接入ESB：已接入
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    报文类型：json
                                </el-col>
                                <el-col :span="12">
                                    通讯协议：HTTP
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    系统状态：在建/审核通过
                                </el-col>
                                <el-col :span="12">
                                    集成方式：内联ESB
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    鉴权类型：不鉴权
                                </el-col>
                                <el-col :span="12">
                                    所属分区：宁夏银行
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    系统编号：ACS
                                </el-col>
                                <el-col :span="12">
                                    系统名称：中央银行会计核算数据集中系统
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                        <el-collapse-item title="部门岗位信息" name="2">
                            <el-row>
                                <el-col :span="12">
                                    技术部门：渠道组
                                </el-col>
                                <el-col :span="12">
                                    应用负责人：wh1811-王浩
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    应用配合人：马国成
                                </el-col>
                                <el-col :span="12">
                                    业务负责部门：开发部
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    业务负责人：母洪涛
                                </el-col>
                                <el-col :span="12">
                                    运维负责人：罗旭
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                        <el-collapse-item title="安全信息" name="3">
                            <el-row>
                                <el-col :span="12">
                                    灾备等级：无
                                </el-col>
                                <el-col :span="12">
                                    灾备模式：无
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    灾备系统情况说明：无
                                </el-col>
                                <el-col :span="12">
                                    安全保护等级：无
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    是否已纳入监控：否
                                </el-col>
                                <el-col :span="12">
                                    是否建立应急预案：是
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                        <el-collapse-item title="服务信息" name="4">
                            <el-row>
                                <el-col :span="12">
                                    服务时间：2022-03-02
                                </el-col>
                                <el-col :span="12">
                                    使用用户：薛向弟
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    服务范围：宁夏银行
                                </el-col>
                                <el-col :span="12">
                                    是否直接面对用户：是
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                        <el-collapse-item title="基本技术属性" name="5">
                            <el-row>
                                <el-col :span="12">
                                    系统类型：windows10
                                </el-col>
                                <el-col :span="12">
                                    访问方式：登录
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    兼容浏览器：谷歌
                                </el-col>
                                <el-col :span="12">
                                    应用服务器高可用技术：分布式
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    应用服务器是否负载均衡：是
                                </el-col>
                                <el-col :span="12">
                                    操作系统：Linux
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    数据库：mysql，oracle
                                </el-col>
                                <el-col :span="12">
                                    中间件：无
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                        <el-collapse-item title="配置项" name="6">
                            <el-row>
                                <el-col :span="12">
                                    是否管控到内部接口：否
                                </el-col>
                            </el-row>
                        </el-collapse-item>
                    </el-collapse>
                </div>

            </el-tab-pane>
            <el-tab-pane label="应用功能" name="two">
                <el-form  ref="queryForm"  :inline="true">
                    <el-form-item label="应用功能查询" >
                        <el-input
                            clearable
                            size="small"
                            style="width: 240px"
                        />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" size="mini" icon="el-icon-search">搜索</el-button>
                    </el-form-item>
                </el-form>
                <el-table >
                    <el-table-column type="selection" width="55" align="center" />
                    <el-table-column label="投产状态" align="center" prop="zdmc" />
                    <el-table-column label="功能编号" align="center" prop="zdms" width="180"/>
                    <el-table-column label="功能名称" align="center" prop="sjlx" />
                    <el-table-column label="功能描述" align="center" prop="cdlx" />
                    <el-table-column label="最后变更日期" align="center" prop="zdcd"/>
                    <el-table-column label="状态" align="center" prop="sjgs" />
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="接口列表" name="three">
                <el-form  ref="queryForm"  :inline="true">
                    <el-form-item label="接口查询" >
                        <el-input
                            clearable
                            size="small"
                            style="width: 240px"
                        />
                    </el-form-item>
                    <el-form-item label="状态" prop="status">
                        <el-select v-model="form.zt">
                            <el-option
                                v-for="dict in zhuangTaiList"
                                :key="dict.value"
                                :label="dict.label"
                                :value="dict.value"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" size="mini" icon="el-icon-search">搜索</el-button>
                    </el-form-item>
                </el-form>
                <el-table >
                    <el-table-column type="selection" width="55" align="center" />
                    <el-table-column label="投产状态" align="center" prop="tczt" />
                    <el-table-column label="接口码" align="center" prop="jkm"/>
                    <el-table-column label="接口名称" align="center" prop="jkmc" />
                    <el-table-column label="接口版本名" align="center" prop="jkbbm" />
                    <el-table-column label="状态" align="center" prop="zt" />
                    <el-table-column label="最后变更日期" align="center" prop="zhbgrq" />
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="消费清单" name="four">
                <scences></scences>
            </el-tab-pane>
            <el-tab-pane label="被消费清单" name="five">
                <wasXF></wasXF>
            </el-tab-pane>
            <el-tab-pane label="集成关系" name="six">
                <div id="system_relation" style="height: 500px"></div>
            </el-tab-pane>
            <el-tab-pane label="系统附件" name="seven">
                <el-table >
                    <el-table-column type="selection" width="55" align="center" />
                    <el-table-column label="文件名称" align="center" prop="wjmc" />
                    <el-table-column label="文件大小" align="center" prop="wjdx"/>
                    <el-table-column label="所属系统" align="center" prop="ssxt" />
                    <el-table-column label="创建时间" align="center" prop="cjsj" />
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="系统报文头" name="eight">
                <sysHeader></sysHeader>
            </el-tab-pane>
        </el-tabs>
    </div>

</template>
<script>
    import echarts from 'echarts';
    import scences from '@/views/system/list/detail/components/service-scenes';
    import wasXF from '@/views/system/list/detail/components/wasConsumption';
    import sysHeader from '@/views/system/list/detail/components/systemHeader';

    export default {
        components: { scences,wasXF,sysHeader },

        data() {
            return {
                activeName: "one",
                activeNames: ['1'],
                zhuangTaiList:[
                    {value:'',label:"--请选择--"},
                    {value:'1',label:"可用"},
                    {value:'2',label: "不可用"}
                ],
                // 表单参数
                form: {},
            };
        },
        mounted() {
            //系统集成关系图
            var echartDom = document.getElementById('system_relation');
            var mychart = echarts.init(echartDom);
            var option;
            option = {
                title: {
                    text: '系统集成关系',
                },
                tooltip: {},
                animationDurationUpdate: 1500,
                animationEasingUpdate: 'quinticInOut',
                series: [
                    {
                        type: 'graph',
                        layout: 'none',
                        symbolSize: 50,
                        roam: true,
                        label: {
                            show: true
                        },
                        edgeSymbol: ['circle', 'arrow'],
                        edgeSymbolSize: [4, 10],
                        edgeLabel: {
                            fontSize: 20
                        },
                        data: [
                            {
                                name: 'MBPCIASO-客户身份认证系统',
                                x: 300,
                                y: 300
                            },
                        ],
                        // links: [],
                        links: [
                            {
                                source: 0,
                                target: 1,
                                symbolSize: [5, 20],
                                label: {
                                    show: true
                                },
                                lineStyle: {
                                    width: 5,
                                    curveness: 0.2
                                }
                            },
                           /* {
                                source: 'Node 2',
                                target: 'Node 1',
                                label: {
                                    show: true
                                },
                                lineStyle: {
                                    curveness: 0.2
                                }
                            },
                            {
                                source: 'Node 1',
                                target: 'Node 3'
                            },
                            {
                                source: 'Node 2',
                                target: 'Node 3'
                            },
                            {
                                source: 'Node 2',
                                target: 'Node 4'
                            },
                            {
                                source: 'Node 1',
                                target: 'Node 4'
                            }*/
                        ],
                        lineStyle: {
                            opacity: 0.9,
                            width: 2,
                            curveness: 0
                        }
                    }
                ]
            };
            mychart.setOption(option);
        },

        methods: {
            getList(){

            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    };
</script>
<style>

    #system_relation{
        width: 500px;
    }
</style>
