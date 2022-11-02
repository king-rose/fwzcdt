<template>
    <div class="app-container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="待办任务" name="first">
                <el-tabs type="border-card">
                    <el-tab-pane label="系统">
                        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
                            <el-table-column type="selection" width="50" align="center"/>
                            <el-table-column label="创建时间" align="center" key="cjsj" prop="cjsj"/>
                            <el-table-column label="项目编号" align="center" key="xmbh" prop="xmbh"/>
                            <el-table-column label="资源类型" align="center" key="zylx" prop="zylx"/>
                            <el-table-column label="操作类型" align="center" key="czlxe" prop="czlxe"/>
                            <el-table-column label="任务说明" align="center" key="rwsm" prop="rwsm"/>
                            <el-table-column label="当前环节" align="center" key="dqhj" prop="dqhj"/>
                            <el-table-column label="发起人" align="center" key="fqr" prop="fqr"/>
                        </el-table>
                        <pagination
                            v-show="total>=0"
                            :total="total"
                            :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize"
                            @pagination="getList"
                        />
                    </el-tab-pane>
                    <el-tab-pane label="服务场景">
                        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
                            <el-table-column type="selection" width="50" align="center"/>
                            <el-table-column label="创建时间" align="center" key="cjsj" prop="cjsj"/>
                            <el-table-column label="项目编号" align="center" key="xmbh" prop="xmbh"/>
                            <el-table-column label="资源类型" align="center" key="zylx" prop="zylx"/>
                            <el-table-column label="操作类型" align="center" key="czlxe" prop="czlxe"/>
                            <el-table-column label="任务说明" align="center" key="rwsm" prop="rwsm"/>
                            <el-table-column label="当前环节" align="center" key="dqhj" prop="dqhj"/>
                            <el-table-column label="发起人" align="center" key="fqr" prop="fqr"/>
                        </el-table>
                        <pagination
                            v-show="total>=0"
                            :total="total"
                            :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize"
                            @pagination="getList"
                        />
                    </el-tab-pane>
                    <el-tab-pane label="消费关系">
                        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
                            <el-table-column type="selection" width="50" align="center"/>
                            <el-table-column label="创建时间" align="center" key="cjsj" prop="cjsj"/>
                            <el-table-column label="项目编号" align="center" key="xmbh" prop="xmbh"/>
                            <el-table-column label="资源类型" align="center" key="zylx" prop="zylx"/>
                            <el-table-column label="操作类型" align="center" key="czlxe" prop="czlxe"/>
                            <el-table-column label="任务说明" align="center" key="rwsm" prop="rwsm"/>
                            <el-table-column label="当前环节" align="center" key="dqhj" prop="dqhj"/>
                            <el-table-column label="发起人" align="center" key="fqr" prop="fqr"/>
                        </el-table>
                        <pagination
                            v-show="total>=0"
                            :total="total"
                            :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize"
                            @pagination="getList"
                        />
                    </el-tab-pane>
                    <el-tab-pane label="接口">
                        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
                            <el-table-column type="selection" width="50" align="center"/>
                            <el-table-column label="创建时间" align="center" key="cjsj" prop="cjsj"/>
                            <el-table-column label="项目编号" align="center" key="xmbh" prop="xmbh"/>
                            <el-table-column label="资源类型" align="center" key="zylx" prop="zylx"/>
                            <el-table-column label="操作类型" align="center" key="czlxe" prop="czlxe"/>
                            <el-table-column label="任务说明" align="center" key="rwsm" prop="rwsm"/>
                            <el-table-column label="当前环节" align="center" key="dqhj" prop="dqhj"/>
                            <el-table-column label="发起人" align="center" key="fqr" prop="fqr"/>
                        </el-table>
                        <pagination
                            v-show="total>=0"
                            :total="total"
                            :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize"
                            @pagination="getList"
                        />
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="在途任务" name="second">
                <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
                    <el-form-item label="模糊查询" prop="gjzcx">
                        <el-input
                            v-model="queryParams.gjzcx"
                            placeholder="请输入关键字"
                            clearable
                            size="small"
                            @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                    <el-form-item label="资源类型" prop="zylx">
                        <el-select v-model="queryParams.zylx" size="small" style="width: 100%">
                            <el-option label="系统申请单" value="0"/>
                            <el-option label="服务申请单" value="1"/>
                            <el-option label="消费关系申请单" value="2"/>
                            <el-option label="主题发布申请单" value="3"/>
                            <el-option label="主题订阅申请单" value="4"/>
                            <el-option label="接口申请单" value="5"/>
                            <el-option label="API申请单" value="6"/>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    </el-form-item>
                </el-form>
                <el-table v-loading="loading" :data="ztrwList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="50" align="center"/>
                    <el-table-column label="创建时间" align="center" width="150" prop="cjsj"/>
                    <el-table-column label="项目编号" align="center" prop="xmbh"/>
                    <el-table-column label="资源类型" align="center" prop="zylx"/>
                    <el-table-column label="操作类型" align="center" prop="czlx"/>
                    <el-table-column label="任务说明" align="center" prop="rwsm" :show-overflow-tooltip="true"/>
                    <el-table-column label="任务状态" align="center" prop="status">
                        <template slot-scope="scope">
                            <span style="color: #1ab394;cursor: pointer" @click="openApprove=true">{{scope.row.status}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="当前环节" align="center" prop="dqhj"/>
                    <el-table-column label="当前环节审批人" align="center" prop="dqhjspr"/>
                    <el-table-column label="发起人" align="center" prop="fqr"/>
                    <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                type="text"
                                icon="el-icon-edit"
                                @click="handleUpdate(scope.row)"
                                v-hasPermi="['system:config:edit']"
                            >查看
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <pagination
                    v-show="total>=0"
                    :total="5"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getZtrwList"
                />
                <div style="margin-bottom: 20px;"></div>

                <!-- 审批流程图 -->
                <el-dialog title="审批流程图" :visible.sync="openApprove">
                    <img
                        style="width: 600px;"
                        :src="baseURL + '/activiti/definition/readResource?pdid=leave:2:10&resourceName=C:/ruoyi/uploadPath/processDefiniton/2022/04/20/99c1000c-aab9-42be-8bae-9c378b9eace2.bpmn.leave.png'"
                    />
                </el-dialog>

                <!-- 查看对话框1 -->
                <!--<el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
                  <el-form ref="form" :model="form"  label-width="80px">
                    <el-container>
                      <el-header style="display: flex;justify-content:space-between;align-items:center">
                          <h3>审批信息</h3>
                          <el-button style="color: #18b4b2;border:2px solid #18b4b2;border-radius:10px;"@click="oPenXfgxDetail">查看消费关系详情</el-button>
                      </el-header>
                      <el-main>
                        <el-row>
                            <el-col :span="12">
                                申请单类型：消费关系申请单
                            </el-col>
                            <el-col :span="12">
                                操作类型：新增
                            </el-col>
                          </el-row>
                        <el-row>
                          <el-col :span="12">
                            项目编号：LZHPOC-泸州银行poc
                          </el-col>
                          <el-col :span="12">
                            申请单归属系统：LZ二代支付
                          </el-col>
                        </el-row>
                        <el-row>
                          <el-col :span="12">
                            审核状态：提交审核中
                          </el-col>
                          <el-col :span="12">
                            创建时间：2022-03-22 14:09:23
                          </el-col>
                        </el-row>
                        <el-row>
                          <el-col :span="12">
                            当前环节：消费方产品经理审批
                          </el-col>
                          <el-col :span="12">
                            当前环节负责人：X_ProManager-X提供方产品经理
                          </el-col>
                        </el-row>
                      </el-main>
                    </el-container>

                    <el-container style="margin-top: 10px">
                      <el-header style="display: flex;justify-content:space-between;align-items: center">
                        <h3>审批记录</h3>
                        <el-button style="border:2px solid #18b4b2;color:#18b4b2;border-radius: 10px">查看审批流程图</el-button>
                      </el-header>
                      <el-main>
                        <el-table v-loading="loading" :data="spjlList" @selection-change="handleSelectionChange">
                          <el-table-column label="审批节点" align="center"  prop="spjd" />
                          <el-table-column label="审批人" align="center"  prop="spr" />
                          <el-table-column label="审批时间" align="center"  prop="time"/>
                          <el-table-column label="审批结果" align="center"  prop="spResult"/>
                          <el-table-column label="审批备注" align="center"   prop="spRemark" />
                        </el-table>
                      </el-main>
                    </el-container>
                  </el-form>
                </el-dialog>-->
                <!-- 查看对话框2-->
                <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
                    <el-tabs v-model="cardName" type="card" @tab-click="handleClick">
                        <el-tab-pane label="基本信息" name="first">
                            <essential-information></essential-information>
                        </el-tab-pane>
                        <el-tab-pane label="应用功能" name="second">
                            <application-function></application-function>
                        </el-tab-pane>
                        <el-tab-pane label="接口列表" name="third">
                            <interface-list></interface-list>
                        </el-tab-pane>
                        <el-tab-pane label="系统调用关系图" name="fourth">定时任务补偿</el-tab-pane>
                        <el-tab-pane label="被消费清单" name="fifth">
                            <consumed-list></consumed-list>
                        </el-tab-pane>
                        <el-tab-pane label="系统附件" name="sixth">
                            <system-accessories></system-accessories>
                        </el-tab-pane>
                        <el-tab-pane label="系统报文头" name="seventh">角色管理</el-tab-pane>
                        <el-tab-pane label="文件路径" name="eight">定时任务补偿</el-tab-pane>
                    </el-tabs>
                </el-dialog>
                <!-- 查看消费关系详情 -->
                <el-dialog :title="title" :visible.sync="openXfgx" width="700px" append-to-body>
                    <el-container>
                        <el-main>
                            <el-row>
                                <el-col :span="12">
                                    消费方系统：LZCNPS2-LZ二代支付
                                </el-col>
                                <el-col :span="12">
                                    消费方系统接口：LZCNPS2-LZ
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    消费方系统功能：测试
                                </el-col>
                                <el-col :span="12">
                                    提供方系统：LZCNPS2-LZ二代支付
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    提供方系统功能：A002-通用记账
                                </el-col>
                                <el-col :span="12">
                                    服务编码：p011002
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    服务中文名：存款服务
                                </el-col>
                                <el-col :span="12">
                                    场景编码：A02
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    场景中文名：通用记账
                                </el-col>
                                <el-col :span="12">
                                    项目编号：LZHPOC
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    状态：审核中
                                </el-col>
                                <el-col :span="12">
                                    超时时间：60000
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    创建时间：2022-02-14 23:14:38
                                </el-col>
                                <el-col :span="12">
                                    业务交易代码：LZRSN
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    业务交易名称：LZ二代支付
                                </el-col>
                            </el-row>
                        </el-main>
                    </el-container>
                </el-dialog>
            </el-tab-pane>
            <el-tab-pane label="已办任务" name="third">
                <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
                    <el-form-item label="模糊查询" prop="gjzcx">
                        <el-input
                            v-model="queryParams.gjzcx"
                            placeholder="请输入关键字"
                            clearable
                            size="small"
                            @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                    <el-form-item label="资源类型" prop="zylx">
                        <el-select v-model="queryParams.zylx" size="small" style="width: 100%">
                            <el-option label="系统申请单" value="0"/>
                            <el-option label="服务申请单" value="1"/>
                            <el-option label="消费关系申请单" value="2"/>
                            <el-option label="主题发布申请单" value="3"/>
                            <el-option label="主题订阅申请单" value="4"/>
                            <el-option label="接口申请单" value="5"/>
                            <el-option label="API申请单" value="6"/>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    </el-form-item>
                </el-form>
                <el-table v-loading="loading" :data="ztrwList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="50" align="center"/>
                    <el-table-column label="创建时间" align="center" width="150" prop="cjsj"/>
                    <el-table-column label="项目编号" align="center" prop="xmbh"/>
                    <el-table-column label="资源类型" align="center" prop="zylx"/>
                    <el-table-column label="操作类型" align="center" prop="czlx"/>
                    <el-table-column label="任务说明" align="center" prop="rwsm" :show-overflow-tooltip="true"/>
                    <el-table-column label="发起人" align="center" prop="fqr"/>
                    <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                type="text"
                                icon="el-icon-edit"
                                @click="jkDetail(scope.row)"
                                v-hasPermi="['system:config:edit']"
                            >查看
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <pagination
                    v-show="total>=0"
                    :total="5"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getZtrwList"
                />
                <div style="margin-bottom: 20px;"></div>
                <!-- 查看对话框 -->
                <el-dialog :title="title" :visible.sync="openjk" width="1000px" append-to-body>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-container>
                            <el-header style="display: flex;justify-content:space-between;align-items:center">
                                <h3>审批信息</h3>
                                <el-button style="color: #18b4b2;border:2px solid #18b4b2;border-radius:10px;"
                                           @click="oPenJkxq">查看接口详情
                                </el-button>
                            </el-header>
                            <el-main>
                                <el-row>
                                    <el-col :span="12">
                                        申请单类型：接口申请单
                                    </el-col>
                                    <el-col :span="12">
                                        操作类型：新增
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        项目编号：LZHPOC-泸州银行poc
                                    </el-col>
                                    <el-col :span="12">
                                        申请单归属系统：LZ二代支付
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        审核状态：审核通过
                                    </el-col>
                                    <el-col :span="12">
                                        创建时间：2022-03-22 14:09:23
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        接口码：A001
                                    </el-col>
                                    <el-col :span="12">
                                        服务编码：G0120002
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        关联服务场景编码：Z03
                                    </el-col>
                                    <el-col :span="12">
                                        关联服务场景名称：对私客户信息更新
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        变更原因：新增
                                    </el-col>
                                    <el-col :span="12">
                                        变更内容：新增
                                    </el-col>
                                </el-row>
                            </el-main>
                        </el-container>
                        <el-container style="margin-top: 10px">
                            <el-header style="display: flex;justify-content:space-between;align-items: center">
                                <h3>审批记录</h3>
                                <el-button style="border:2px solid #18b4b2;color:#18b4b2;border-radius: 10px">查看审批流程图
                                </el-button>
                            </el-header>
                            <el-main>
                                <el-table v-loading="loading" :data="spjlList"
                                          @selection-change="handleSelectionChange">
                                    <el-table-column label="审批节点" align="center" prop="spjd"/>
                                    <el-table-column label="审批人" align="center" prop="spr"/>
                                    <el-table-column label="审批时间" align="center" prop="time"/>
                                    <el-table-column label="审批结果" align="center" prop="spResult"/>
                                    <el-table-column label="审批备注" align="center" prop="spRemark"/>
                                </el-table>
                            </el-main>
                        </el-container>
                    </el-form>
                </el-dialog>
                <!-- 查看接口详情 -->
                <el-dialog :title="title" :visible.sync="jkxq" width="1200px" append-to-body>
                    <el-tabs type="border-card">
                        <el-tab-pane label="接口属性">
                            <el-row>
                                <el-col :span="8">
                                    接口码：A001
                                </el-col>
                                <el-col :span="8">
                                    接口名称：账户余额查询
                                </el-col>
                                <el-col :span="8">
                                    版本号：1.0
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="8">
                                    状态：已发布
                                </el-col>
                                <el-col :span="8">
                                    投产状态：设计态
                                </el-col>
                                <el-col :span="8">
                                    通讯协议：TCP
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="8">
                                    报文格式：XML
                                </el-col>
                                <el-col :span="8">
                                    接口描述：账户额查询
                                </el-col>
                                <el-col :span="8">
                    <span style="color: #78ced0;font-size: 5px">
                      ESB项目_接口定义_ZHT03_A001账户余额查询_v1.0xlsx
                    </span>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="接口定义">
                            <el-container>
                                <el-header style="display: flex;align-items: center;height: 40px">输入字段（3）</el-header>
                                <el-main>
                                    <el-table v-loading="loading" :data="jkdyList">
                                        <el-table-column label="字段序号" align="center" width="150" prop="zdxh"/>
                                        <el-table-column label="字段中文名" align="center" prop="zdzwm"/>
                                        <el-table-column label="字段英文名" align="center" prop="zdywm"/>
                                        <el-table-column label="数据类型" align="center" prop="sjlx"/>
                                        <el-table-column label="字段长度" align="center" prop="zdcd"/>
                                        <el-table-column label="小数位" align="center" prop="xsw"/>
                                        <el-table-column label="默认值" align="center" prop="mrz"/>
                                        <el-table-column label="是否必输" align="center" prop="sfbs"/>
                                        <el-table-column label="枚举列表" align="center" prop="mjlb"/>
                                        <el-table-column label="字段位置" align="center" prop="zdwz"
                                                         :show-overflow-tooltip="true"/>
                                        <el-table-column label="备注" align="center" prop="remark"/>
                                    </el-table>
                                </el-main>
                            </el-container>

                            <el-container>
                                <el-header style="display: flex;align-items: center;height: 40px">输出字段（3）</el-header>
                                <el-main>
                                    <el-table v-loading="loading" :data="jkdyscList">
                                        <el-table-column label="字段序号" align="center" width="150" prop="zdxh"/>
                                        <el-table-column label="字段中文名" align="center" prop="zdzwm"/>
                                        <el-table-column label="字段英文名" align="center" prop="zdywm"/>
                                        <el-table-column label="数据类型" align="center" prop="sjlx"/>
                                        <el-table-column label="字段长度" align="center" prop="zdcd"/>
                                        <el-table-column label="小数位" align="center" prop="xsw"/>
                                        <el-table-column label="默认值" align="center" prop="mrz"/>
                                        <el-table-column label="是否必输" align="center" prop="sfbs"/>
                                        <el-table-column label="枚举列表" align="center" prop="mjlb"/>
                                        <el-table-column label="字段位置" align="center" prop="zdwz"
                                                         :show-overflow-tooltip="true"/>
                                        <el-table-column label="备注" align="center" prop="remark"/>
                                    </el-table>
                                </el-main>
                            </el-container>
                        </el-tab-pane>
                        <el-tab-pane label="被消费列表">
                            <el-table v-loading="loading" :data="bxfList">
                                <el-table-column label="消费方" align="center" width="150" prop="xff"/>
                                <el-table-column label="服务编号" align="center" prop="fwbh"/>
                                <el-table-column label="服务名称" align="center" prop="fwmc"/>
                                <el-table-column label="场景码" align="center" prop="cjm"/>
                                <el-table-column label="场景名称" align="center" prop="cjmc"/>
                                <el-table-column label="提供方" align="center" prop="tgf"/>
                                <el-table-column label="消费时间" align="center" prop="xfTime"/>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="场景列表">
                            <el-table v-loading="loading" :data="cjList">
                                <el-table-column label="场景码" align="center" width="150" prop="cjm"/>
                                <el-table-column label="场景名称" align="center" prop="cjmc"/>
                                <el-table-column label="场景描述" align="center" prop="cjms"/>
                                <el-table-column label="服务编号" align="center" prop="fwbh"/>
                            </el-table>
                        </el-tab-pane>
                    </el-tabs>
                </el-dialog>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
    import EssentialInformation from '@/views/workbench/components/essential-information';
    import ApplicationFunction from '@/views/workbench/components/application-function';
    import InterfaceList from '@/views/workbench/components/interface-list';
    import ConsumedList from '@/views/workbench/components/consumed-list';
    import SystemAccessories from '@/views/workbench/components/system-accessories'

    export default {
        components: {EssentialInformation, ApplicationFunction, InterfaceList, ConsumedList, SystemAccessories},
        data() {
            return {
                jkdyList: [],
                activeName: "second",
                cardName: "first",
                loading: false,
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                total: 0,
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    gjzcx: undefined,
                    zylx: undefined,
                },
                list: [],
                ztrwList: [],
                spjlList: [],
                form: [],
                cjList: [],
                // 显示搜索条件
                showSearch: true,
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                openjk: false,
                openApprove:false,
                // 表单参数
                form: {},
                openXfgx: false,
                jkxq: false,
                jkdyscList: [],
                bxfList: [],
                baseURL: process.env.VUE_APP_BASE_API,
            };
        },
        created() {
            this.getList();
            this.getZtrwList();
            this.getSpjlList();
            this.getJkdyList();
            this.getJkdyscList();
            this.getBxfList();
            this.getCjList();
        },
        methods: {
            getList() {
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
            },
            getBxfList() {
                this.bxfList = [
                    {
                        xff: '马国成',
                        fwbh: 'G0120002',
                        fwmc: '项目管理',
                        cjm: 'M03',
                        cjmc: '对私客户信息更新',
                        tgf: 'MGC03',
                        xfTime: '2022-03-18 11:42:55'
                    }
                ]
            },
            getJkdyList() {
                this.jkdyList = [
                    {
                        zdxh: '3',
                        zdzwm: '账号',
                        zdywm: 'accno',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                    {
                        zdxh: '1',
                        zdzwm: '机构号',
                        zdywm: 'brno',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                    {
                        zdxh: '2',
                        zdzwm: '柜员号',
                        zdywm: 'tlrno',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                ]
            },
            getJkdyscList() {
                this.jkdyscList = [
                    {
                        zdxh: '3',
                        zdzwm: '余额',
                        zdywm: 'balance',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                    {
                        zdxh: '1',
                        zdzwm: '返回码',
                        zdywm: 'ResponseCode',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                    {
                        zdxh: '2',
                        zdzwm: '返回信息',
                        zdywm: 'ResponseCode',
                        sjlx: '字符型',
                        zdcd: 16,
                        xsw: 2,
                        mrz: '无',
                        sfbs: '否',
                        mjlb: '测试',
                        zdwz: 'Envelope/Body/getAcction',
                        remark: '测试'
                    },
                ]
            },
            getZtrwList() {
                this.ztrwList = [
                    {
                        cjsj: '2022-03-21 17:53:09',
                        xmbh: 'LZHPOC',
                        zylx: '系统申请单',
                        czlx: '新增',
                        rwsm: 'LZHPOC需求编号:admin用户新增了tzDD1系统[侧篇]',
                        status:'待审批',
                        dqhj: '产品经理审批',
                        dqhjspr: 'tz_reqManager-消费方产品',
                        fqr: 'admmin-管理员'
                    },
                    {
                        cjsj: '2022-03-16 11:31:21',
                        xmbh: 'pocSDBank',
                        zylx: 'API申请单',
                        czlx: '新增',
                        rwsm: 'X_ProManager用户新增了由Demo[微服务演示]发布的/demo/postDemoJsonAPl[微服务演示]',
                        status:'待审批',
                        dqhj: '服务分析经理',
                        dqhjspr: 'POLE_ANALYST-ROLE',
                        fqr: 'X_ProManager'
                    },
                    {
                        cjsj: '2022-02-14 23:15:00',
                        xmbh: 'pocSDBank',
                        zylx: '消费关系申请单',
                        czlx: '新增',
                        rwsm: 'LZHPOC需求编号:admin用户新增了LZCORBS系统',
                        status:'待审批',
                        dqhj: '消费方产品经理审批',
                        dqhjspr: 'X_ProManager-x',
                        fqr: 'admin管理员'
                    },
                    {
                        cjsj: '2022-02-14 23:14:38',
                        xmbh: 'LZHPOC',
                        zylx: '消费关系申请单',
                        czlx: '新增',
                        rwsm: 'LZHPOC需求编号:admin用户新增了LZCORBS系统',
                        status:'待审批',
                        dqhj: '消费方产品经理审批',
                        dqhjspr: 'X_ProManager-x',
                        fqr: 'admin管理员'
                    },
                    {
                        cjsj: '2022-02-14 23:14:15',
                        xmbh: 'LZHPOC',
                        zylx: '消费关系申请单',
                        czlx: '新增',
                        rwsm: 'LZHPOC需求编号:admin用户新增了LZCORBS系统',
                        status:'待审批',
                        dqhj: '消费方产品经理审批',
                        dqhjspr: 'X_ProManager-x',
                        fqr: 'admin管理员'
                    }
                ]
            },
            getSpjlList() {
                this.spjlList = [
                    {
                        spjd: '提交申请',
                        spr: '管理员',
                        time: '2022-03-23 14:55:40',
                        spResult: '批准',
                        spRemark: '发起流程'
                    },
                    {
                        spjd: '消费方产品经理审批',
                        spr: '管理员',
                        time: '2022-03-23 15:00:20',
                        spResult: '待审核',
                        spRemark: '待审核'
                    }
                ]
            },
            jkDetail() {
                this.openjk = true
                this.title = '审批详情'
            },
            oPenJkxq() {
                this.jkxq = true
                this.title = '接口详情'
            },
            getCjList() {
                this.cjList = [{
                    cjm: 'M03',
                    cjmc: '对私客户信息更新',
                    cjms: '对私客户信息管理',
                    fwbh: 'G120002'
                }]
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.open = true;
                this.title = "审批详情";
            },
            /**消费关系详情*/
            oPenXfgxDetail() {
                this.openXfgx = true
                this.title = '消费关系详情'
            },
            /** 重置按钮操作 */
            resetQuery() {
                /*this.resetForm("queryForm");*/
                this.handleQuery();
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.userId);
                this.single = selection.length != 1;
                this.multiple = !selection.length;
            },
        }
    };
</script>
<style lang="scss" scoped>
    .el-header {
        background-color: #f5f7fa;
        color: #333;
        border: 1px solid #e8e8e8;
        border-bottom: none;
    }

    .el-main {
        background-color: #FFFFFF;
        color: #333;
        border: 1px solid #e8e8e8;
    }
</style>
