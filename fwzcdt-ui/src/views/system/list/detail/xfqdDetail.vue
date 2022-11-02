<template>
    <div class="app-container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="基本信息" name="one">
            <div style="width: 85%;margin: 0 auto;">
              <el-collapse v-model="activeNames" @change="handleClick">
                <el-collapse-item title="基本信息" name="1" class="yangShi">
                  <el-row>
                    <el-col :span="12">
                      关联接口：MbsdCore-1000-039001
                    </el-col>
                    <el-col :span="12">
                      关联接口所属系统：核心交易系统
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      项目编号：BNX_SCENE_CBTS-核心交易系统服务场景
                    </el-col>
                    <el-col :span="12">
                      申请单：BNX_SCENE_CBTS需求编号maguocheng用户升级了由CBTS核心交易系统发布的ChargeAcctF01(MbsdCore-1001-039001)
                      服务场景[核心多借多贷通用记账]
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      变更原因：数据字典中将Num类型的字段变更为String类型
                    </el-col>
                    <el-col :span="12">
                      变更内容：变更字段DR_TOTAL_AMT,CR_TOTAL_AMT,
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      UAT计划投产时间：2022-03-02
                    </el-col>
                    <el-col :span="12">
                      集成方式：内联ESB
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      是否适配：不适配
                    </el-col>
                    <el-col :span="12">
                      是否对标：对标通过
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      是否校验：校验
                    </el-col>
                    <el-col :span="12">
                      最后更新时间：2022-03-07 14:32:27
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      服务场景描述：核心通用记账交易(多借多贷)
                    </el-col>
                    <el-col :span="12">
                      SIT部署记录：已部署
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      报文格式：json
                    </el-col>
                    <el-col :span="12">
                      通讯协议：HTTP
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      HTTP URL：/
                    </el-col>
                    <el-col :span="12">
                      场景类型：标准场景
                    </el-col>
                  </el-row>
                </el-collapse-item>
              </el-collapse>
            </div>
            <scences-detail></scences-detail>
          </el-tab-pane>
          <el-tab-pane label="场景指标" name="two">
            <el-container>
              <el-header>场景指标信息</el-header>
              <el-main>
                <el-row>
                  <el-col :span="4">
                    服务窗口时间：
                  </el-col>
                  <el-col :span="2">
                      20ms
                  </el-col>
                  <el-col :span="4">
                    服务超时时间：
                  </el-col>
                  <el-col :span="2">
                    60000ms
                  </el-col>
                  <el-col :span="4">
                    并发用户数：
                  </el-col>
                  <el-col :span="2">
                    100000
                  </el-col>
                  <el-col :span="4">
                    TPS-每秒交易笔数：
                  </el-col>
                  <el-col :span="2">
                    3500
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="4">
                    服务场景生效时间：
                  </el-col>
                  <el-col :span="2">
                    30ms
                  </el-col>
                  <el-col :span="4">
                    服务场景失败时间：
                  </el-col>
                  <el-col :span="2">
                    30000ms
                  </el-col>
                  <el-col :span="4">
                    优先级：
                  </el-col>
                  <el-col :span="2">
                    9
                  </el-col>
                </el-row>
              </el-main>
            </el-container>
          </el-tab-pane>
          <el-tab-pane label="场景版本历史" name="three">
            <div class="block">
              <el-timeline>
                <el-timeline-item timestamp="2018/4/12" placement="top">
                  <el-card>
                    <h1>F01-核心多借多贷通用记账</h1>
                    <p>项目编号：BNX_SCENE_CBTS</p>
                    <p>服务名：统一记账</p>
                    <p>状态：可用</p>
                    <p>文件：ChargeAcctF01.xls</p>
                    <p>资源包：F01.zip</p>
                  </el-card>
                </el-timeline-item>
                <el-timeline-item timestamp="2018/4/3" placement="top">
                  <el-card>
                    <h1>F01-核心多借多贷通用记账</h1>
                    <p>项目编号：BNX_SCENE_CBTS</p>
                    <p>服务名：统一记账</p>
                    <p>状态：可用</p>
                    <p>文件：ChargeAcctF01.xls</p>
                    <p>资源包：F01.zip</p>
                  </el-card>
                </el-timeline-item>
                <el-timeline-item timestamp="2018/4/2" placement="top">
                  <el-card>
                    <h1>F01-核心多借多贷通用记账</h1>
                    <p>项目编号：BNX_SCENE_CBTS</p>
                    <p>服务名：统一记账</p>
                    <p>状态：可用</p>
                    <p>文件：ChargeAcctF01.xls</p>
                    <p>资源包：F01.zip</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
            </div>
          </el-tab-pane>
          <el-tab-pane label="场景定义" name="four">
            <el-table :data="cjdyData" @row-click="chooseOne"  @current-change="handleSelectionChange" height="450">
              <el-table-column width="55" align="center" >
                <template slot-scope="data">
                  <el-radio v-model="radio" :label="data.row.id">
                    <span></span>
                  </el-radio>
                </template>                                                                             
              </el-table-column>
              <el-table-column type="index" label="序号" ></el-table-column>
              <el-table-column prop="zdywm" label="字段英文名" width="120px"></el-table-column>
              <el-table-column prop="zdzwm" label="字段中文名" width="120px"></el-table-column>
              <el-table-column prop="sjlx" label="数据类型" ></el-table-column>
              <el-table-column prop="zdcd" label="字段长度" ></el-table-column>
              <el-table-column prop="zdcdlx" label="字段长度类型" width="120px"></el-table-column>
              <el-table-column prop="zdxsw" label="字段小数位" width="120px"></el-table-column>
              <el-table-column prop="zdmrz" label="字段默认值" width="120px"></el-table-column>
              <el-table-column prop="sfbs" label="是否必输" ></el-table-column>
              <el-table-column prop="xhcs" label="循环次数" ></el-table-column>
              <el-table-column prop="sjgs" label="数据格式" width="120px"></el-table-column>
              <el-table-column prop="mjlb" label="枚举列表" ></el-table-column>
              <el-table-column prop="zdms" label="字段描述" ></el-table-column>
              <el-table-column prop="zdlj" label="字段路径" width="170px"></el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="被消费列表" name="five">
            <div style="border: #dcdfe6 1px solid;border-bottom: none">
              <el-table :data="bxfList">
                <el-table-column label="排序序列" type="index" width="120px"></el-table-column>
                <el-table-column label="系统编号" prop="xtbh"></el-table-column>
                <el-table-column label="系统名称" prop="xtmc" width="180px"></el-table-column>
                <el-table-column label="系统负责人" prop="xtfzr"></el-table-column>
                <el-table-column label="所属部门" prop="ssbm" ></el-table-column>
                <el-table-column label="系统概述" prop="xtgs" ></el-table-column>
                <el-table-column label="系统创建时间" prop="xtcjsj"></el-table-column>
                <el-table-column label="系统消费时间" prop="xtxfsj"></el-table-column>
              </el-table>
            </div>
            <pagination
              v-show="total>=0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
            <div style="margin-bottom: 20px;"></div>
          </el-tab-pane>
        </el-tabs>
    </div>

</template>
<script>
    import scencesDetail from '@/views/system/list/detail/components/service-detail';

    export default {
        components: { scencesDetail},

        data() {
            return {
                radio:null,
                total:0,
                currentSelectItem: {}, //当前选中的值
                activeName: "one",
                activeNames: ['1'],
                //查询参数
                queryParams:{
                  pageNum:1,
                  pageSize:10
                },
              cjdyData:[],
              bxfList:[]
            };
        },
        created() {
            this.getList();
        },

        methods: {
          chooseOne(row){
            console.log(row)
            this.radio = row.id
          },
          handleSelectionChange(row) {
            this.currentSelectItem = row;
          },
            getList(){
              this.cjdyData=[
                {id:1,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:2,zdywm:'DR_TOTAL_AMT', zdzwm:'借方总金额', sjlx:'String', zdcd:'17', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_TOTAL_AMT'},
                {id:3,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:4,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:5,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:6,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:7,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:8,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:9,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:10,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:11,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:12,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:13,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:14,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:15,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:16,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:17,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},
                {id:18,zdywm:'DR_NO_ITEMS', zdzwm:'借方笔数', sjlx:'String', zdcd:'20', zdcdlx:'最长', zdxsw:'2', zdmrz:'无', sfbs:'否', xhcs:10,sjgs:'Decimal(17,2)', mjlb:'无', zdms:'无', zdlj:'/BODY/DR_NO_ITEMS'},

              ],
              this.bxfList=[{
                xtbh:'MBPWSML',
                xtmc:'中间业务平台如意宁税贷',
                xtfzr:'马国成',
                ssbm:'渠道组',
                xtgs:'综合理财平台',
                xtcjsj:'2022-03-09 13:33:28',
                xtxfsj:'2022-03-10 15:30:20'
              }]
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
    .el-header{
      background-color: #f7f7f7;
      border: solid 1px #e2e2e2;
      font-size: 20px;
      border-bottom:none;
      line-height: 60px;
    }
    .el-main {
      line-height: 60px;
      font-size: 15px;
      border: solid 1px #e2e2e2;
    }
</style>
