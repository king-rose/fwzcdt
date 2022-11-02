<template>
	<div class="app-container">
		<div class="app-container">
			<el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="150px">
				<el-tabs v-model="activeName" @tab-click="handleClick">
					<el-tab-pane name="mySystem" style="margin: 0 auto;background: #f8f8f9">
						<span slot="label" class="tab-label">我的系统</span>

						<el-form-item label="系统查询" prop="sysName" style="margin-top: 10px;margin-left: 20px">
							<el-input
								v-model="queryParams.sysName"
								clearable
								placeholder=""
								size="default"
								style="width:800px;"
								@clear.capture="handleQuery"
								@keyup.enter.native="handleQuery"
							>
								<el-button slot="append" icon="el-icon-search" @click="handleQuery"></el-button>
							</el-input>
						</el-form-item>
					</el-tab-pane>
					<el-tab-pane name="allSystem" style="margin: 0 auto;background: #f8f8f9">
						<span slot="label" class="tab-label">全行应用系统</span>
						<div style="margin-left: 20px;">
							<el-form-item label="系统查询" prop="groupQuery" style="margin-top: 10px">
								<el-switch
									v-model="queryParams.switchs"
									active-color="#409EFF"
									active-text="组合查询"
									inactive-color="#13ce66"
									inactive-text="模糊查询"
									style="margin-left: 35px">
								</el-switch>

							</el-form-item>
							<el-form-item style="margin-top: 10px;">
								<el-input
									v-model="queryParams.sysName"
									clearable
									placeholder=""
									size="default"
									style="width:800px;"
									@keyup.enter.native="handleQuery"
								>
									<el-button slot="append" icon="el-icon-search" @click="handleQuery"></el-button>
								</el-input>
							</el-form-item>
						</div>
					</el-tab-pane>
				</el-tabs>
				<!--组合查询-->
				<div v-if="queryParams.switchs" class="mt20">
					<el-row :gutter="10">
						<el-col :span="6">
							<el-form-item label="负责人:">
								<el-select v-model="queryParams.managerId" filterable>
									<el-option label="--请选择--" value=""></el-option>
									<el-option
										v-for="item in personOptions"
										:key="item.value"
										:label="item.label"
										:value="item.value">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="6">
							<el-form-item label="所属层:">
								<el-select v-model="queryParams.systemLayer" placeholder="--请选择--">
									<el-option
										v-for="item in sysLayerOptions"
										:key="item"
										:label="item"
										:value="item">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-form-item label="技术部门:">
							<el-select v-model="queryParams.techDept" placeholder="--请选择--">
								<el-option label="--请选择--" value=""></el-option>
								<el-option
									v-for="item in techDeptOptions"
									:key="item"
									:label="item"
									:value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-row>
					<el-row :gutter="10">
						<el-col :span="6">
							<el-form-item label="状态:">
								<el-select v-model="queryParams.sysStatus">
									<el-option label="--请选择--" value=""></el-option>
									<el-option
										v-for="item in stateOptions"
										:key="item.dictValue"
										:label="item.dictLabel"
										:value="item.dictValue">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
<!--						<el-col :span="6">
							<el-form-item label="用途:">
								<el-select v-model="queryParams.use" placeholder="&#45;&#45;请选择&#45;&#45;">
									<el-option label="&#45;&#45;请选择&#45;&#45;" value=""></el-option>
									<el-option label="提供方" value="1"></el-option>
									<el-option label="消费方" value="0"></el-option>
								</el-select>
							</el-form-item>
						</el-col>-->
						<el-col :span="6">
							<el-form-item label="是否已接入:">
								<el-select v-model="queryParams.connectToEsb" placeholder="--请选择--">
									<el-option label="--请选择--" value=""></el-option>
									<el-option label="是" value="1"></el-option>
									<el-option label="否" value="0"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
				</div>
			</el-form>

		</div>


		<el-row :gutter="10" class="mb8">

			<el-col :span="1.5">
				<el-button
					icon="el-icon-plus"
					plain
					size="large"
					type="primary"
					@click="handleAdd"
				>新增
				</el-button>
			</el-col>
		</el-row>

		<el-table
			ref="Table"
			v-loading="loading"
			:cell-style="cellStyle"
			:data="sysList"
			highlight-current-row
			@row-click="chooseOne"
			@current-change="handleSelectionChange"
		>
			<el-table-column align="center" min-width="5%">
				<template slot-scope="scope">
					<el-radio v-model="radio" :label="scope.row.sysId">{{ "" }}</el-radio>
				</template>
			</el-table-column>
			<el-table-column align="center" label="系统编号" min-width="10%" prop="sysCode"></el-table-column>
			<el-table-column align="center" label="系统名称" min-width="20%" prop="sysName">
				<template slot-scope="scope">
					<router-link :to="{ path:'/yqyd/jbxx'}">
						<span :title="scope.row.sysName" class="cell-hover">{{ scope.row.sysName }}</span>
					</router-link>
				</template>
			</el-table-column>
			<el-table-column align="center" label="系统所属层" min-width="15%" prop="systemLayer"></el-table-column>
			<el-table-column align="center" label="技术部门" min-width="15%" prop="techDept"></el-table-column>
			<el-table-column align="center" label="应用负责人" min-width="15%" prop="nickName"></el-table-column>

			<el-table-column :formatter="stateFormatter" align="center" label="系统状态" min-width="15%"
							 prop="sysStatus"></el-table-column>

		</el-table>
		<pagination
			v-show="total>0"
			:limit.sync="queryParams.pageSize"
			:page.sync="queryParams.pageNum"
			:total="total"
			@pagination="getList"
		/>


		<!-- 添加或修改菜单对话框 -->
		<el-dialog :title="title" :visible.sync="open" append-to-body class="abow_dialog" width="1280px" @close="close">
			<div style="width: 650px;margin: 20px auto">
				<el-steps :active="active" finish-status="success" process-status="finish">
					<el-step :title="active===0 ?stepTitle[0]:stepTitle[1]"></el-step>
					<el-step
						:title="active>0 && active<2 ?stepTitle[0]: active===0 ? stepTitle[2]:stepTitle[1]"></el-step>
					<el-step :title="active<2 ?stepTitle[3]: active===2 ? stepTitle[0]:stepTitle[1]"></el-step>
				</el-steps>
			</div>
			<el-divider></el-divider>
			<el-form ref="form1" :model="form" :rules="rules" label-width="200px">
				<!--步骤1-->
				<div v-show="active===0">
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">基本信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="8">
									<el-form-item label="系统编号:" prop="sysCode" required>
										<el-input v-model="form.sysCode" placeholder="请输入编号" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="系统名称:" prop="sysName" required>
										<el-input v-model="form.sysName" placeholder="请输入系统名称" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="系统概述:" prop="sysDes">
										<el-input v-model="form.sysDes" placeholder="请输入系统概述" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="8">
									<el-form-item label="系统所属层:" prop="systemLayer" required>
										<el-select v-model="form.systemLayer" size="large" style="width:100%">
											<el-option v-for="dict in sysLayerOptions"
													   :value="dict"
													   :label="dict"
													   :key="dict">
											</el-option>
										</el-select>
									</el-form-item>
								</el-col>
<!--								<el-col :span="8">
									<el-form-item label="区域名称:" prop="areaName" required>
										<el-select v-model="form.areaName" size="large" style="width:100%">
											<el-option label="宁夏银行总行" value="0"/>
											<el-option label="宁夏银行西安分行" value="1"/>
											<el-option label="宁夏银行天津分行" value="2"/>
										</el-select>
									</el-form-item>
								</el-col>-->
								<el-col :span="8">
									<el-form-item label="开发厂商:" prop="developer">
										<el-input v-model="form.developer" placeholder="请输入开发厂商"
												  size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="8">
									<el-form-item label="超时时间(ms):" prop="timeOut" required>
										<el-input v-model="form.timeOut" placeholder="61000" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="系统上线日期:" prop="onlineDate">
										<el-date-picker
											v-model="form.onlineDate"
											placeholder="选择日期"
											style="width:100%"
											type="date">
										</el-date-picker>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="系统下线日期:" prop="offlineDate">
										<el-date-picker
											v-model="form.offlineDate"
											placeholder="选择日期"
											style="width:100%"
											type="date">
										</el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="8">
									<el-form-item label="是否接入ESB:" prop="connectToEsb">
										<el-select v-model="form.connectToEsb" size="large" style="width:100%">
											<el-option label="是" value="0"/>
											<el-option label="否" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>

								<el-col :span="8">
									<el-form-item label="渠道编码:" prop="channelCode">
										<el-input v-model="form.channelCode" placeholder="" size="large"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="8">
									<el-form-item label="通讯协议:" prop="communicationProtocol">
										<el-select v-model="form.communicationProtocol" size="large" style="width:100%">
											<el-option label="http" value="0"/>
											<el-option label="https" value="1"/>
											<el-option label="tcp" value="2"/>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="8">
									<el-form-item label="报文类型:" prop="messageFormat">
										<el-select v-model="form.messageFormat" size="large" style="width:100%">
											<el-option label="json" value="0"/>
											<el-option label="FIX" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>

								<el-col :span="8">
<!--									<el-form-item label="所属分区:" prop="sysPartition">
										<el-select v-model="form.sysPartition" size="large" style="width:100%">
											<el-option label="first" value="0"/>
											<el-option label="others" value="1"/>
										</el-select>
									</el-form-item>-->
								</el-col>

								<el-col :span="8">
									<el-form-item label="集成方式:" prop="integrationMode">
										<el-select v-model="form.integrationMode" size="large" style="width:100%">
											<el-option label="内联ESB" value="0"/>
											<el-option label="外联ESB" value="1"/>
											<el-option label="直连" value="2"/>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>
						</div>
					</el-card>

					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">部门岗位信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="8">
									<el-form-item label="应用负责人:" prop="managerId" required>
										<el-select v-model="form.managerId" size="large" style="width:100%">
											<el-option v-for="dict in personOptions"
											   :value="dict.value"
											   :label="dict.label"
											   :key="dict.value">
											</el-option>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="技术部门:" prop="techDept">
										<el-select v-model="form.techDept">
											<el-option
												v-for="item in techDeptOptions"
												:key="item"
												:label="item"
												:value="item">
											</el-option>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="应用配合人:" prop="appCoorUsers">
										<el-select v-model="form.appCoorUsers" size="large" style="width:100%">
											<el-option v-for="dict in personOptions"
											   :value="dict.value"
											   :label="dict.label"
											   :key="dict.value">
											</el-option>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="8">
									<el-form-item label="业务负责部门:" prop="busiDept">
										<el-input v-model="form.busiDept" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="业务负责人:" prop="busiLeader">
										<el-input v-model="form.busiLeader" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="运维负责人:" prop="oprLeader">
										<el-input v-model="form.oprLeader" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</div>
					</el-card>
					<el-row>
						<el-col :span="8">
							<el-form-item label="鉴权类型:" prop="authType">
								<el-select v-model="form.authType" size="large" style="width:100%">
									<el-option value="0" label="不认证" />
									<el-option value="1" label="数字认证" />
									<el-option value="2" label="Oauth认证" />
									<el-option value="3" label="数字认证和Oauth认证" />
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
				</div>
			</el-form>
			<el-form ref="form2" :model="form" :rules="rules" label-width="200px">
				<!--步骤2-->
				<div v-show="active===1">
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">安全信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="12">
									<el-form-item label="灾备等级:" prop="disaLevel">
										<el-input v-model="form.disaLevel" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="灾备模式:" prop="disaMode">
										<el-input v-model="form.disaMode" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="灾备恢复时间目标RTO(分钟):" prop="disaRecRtoTime">
										<el-input v-model="form.disaRecRtoTime" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="灾备恢复点目标RPO(分钟):" prop="disaRecRpoTime">
										<el-input v-model="form.disaRecRpoTime" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="灾备系统情况说明:" prop="disaSystemNote">
										<el-input v-model="form.disaSystemNote" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="安全保护等级:" prop="secLevel">
										<el-input v-model="form.secLevel" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="是否建立应急预案:" prop="contPlan">
										<el-select v-model="form.contPlan" size="large" style="width:100%">
											<el-option label="否" value="0"/>
											<el-option label="是" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="是否已纳入监控:" prop="monitor">
										<el-select v-model="form.monitor" size="large" style="width:100%">
											<el-option label="否" value="0"/>
											<el-option label="是" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>
						</div>
					</el-card>

					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">服务信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="12">
									<el-form-item label="服务时间:" prop="serviceTime">
										<el-input v-model="form.serviceTime" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="使用用户:" prop="sysUser">
										<el-input v-model="form.sysUser" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="服务范围" prop="serviceArea">
										<el-input v-model="form.serviceArea" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="是否直接面对客户" prop="faceToCust">
										<el-select v-model="form.faceToCust" size="large" style="width:100%">
											<el-option label="否" value="0"/>
											<el-option label="是" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>


						</div>
					</el-card>

					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">基本技术信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="12">
									<el-form-item label="系统类型:" prop="sysType">
										<el-input v-model="form.sysType" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="访问方式:" prop="accessMode">
										<el-input v-model="form.accessMode" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="兼容浏览器" prop="compBrowser">
										<el-input v-model="form.compBrowser" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="应用服务器高可用技术" prop="highAvailableTech">
										<el-input v-model="form.highAvailableTech" size="large"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="12">
									<el-form-item label="操作系统" prop="oprSystem">
										<el-input v-model="form.oprSystem" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="应用服务器是否负载均衡" prop="loadBalance">
										<el-select v-model="form.loadBalance" size="large" style="width:100%">
											<el-option label="否" value="0"/>
											<el-option label="是" value="1"/>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="数据库" prop="sysDatabase">
										<el-input v-model="form.sysDatabase" size="large"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="中间件" prop="middleware">
										<el-input v-model="form.middleware" size="large"></el-input>
									</el-form-item>
								</el-col>

							</el-row>
						</div>
					</el-card>
				</div>
			</el-form>
			<el-form ref="form3" :model="form" :rules="rules" label-width="200px">
				<!--步骤3-->
				<div v-show="active===2">
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span class="title">基本技术信息</span>
						</div>
						<div>
							<el-row>
								<el-col :span="12">
									<el-form-item label="是否管控到内部接口:" prop="innerJkPass">
										<el-select v-model="form.innerJkPass" size="large" style="width:100%">
											<el-option label="是" value="1"/>
											<el-option label="否" value="0"/>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="所属项目:" prop="project" required>
										<el-select v-model="form.project" size="large" style="width:100%">
											<el-option v-for="dict in projectOptions"
											   :label="dict.projectName"
											   :value="dict.projectCode"
											   :key="dict.projectCode"
											>
											</el-option>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>
						</div>
					</el-card>
					<!--动态表格-->
					<div class="tableDate">
						<div class="button" style="width:6%;float:right;">
							<P>
								<el-button class="el-icon-plus" @click.prevent="addRow()"></el-button>
							</P>
							<p>
								<el-button class="el-icon-minus" @click.prevent="delData()"></el-button>
							</p>
						</div>
						<div class="table">
							<el-table
								ref="table"
								:data="tableData"
								border
								stripe
								style="width: 70%;margin: 0 auto"
								tooltip-effect="dark"
								@selection-change='selectRow'>
								<el-table-column align="center" type="selection" width="45"></el-table-column>

								<el-table-column align="center" label="用途">
									<template slot-scope="scope">
										<el-select v-model="scope.row.use" class="post_name" prop="use">
											<el-option label="消费方" value="0"/>
											<el-option label="提供方" value="1"/>
											<el-option label="发布方" value="2"/>
											<el-option label="订阅方" value="3"/>
										</el-select>
									</template>
								</el-table-column>
								<el-table-column label="报文编码">
									<template slot-scope="scope">
										<el-select v-model="scope.row.code" class="post_name" prop="code">
											<el-option label="GB18030" value="0"/>
											<el-option label="GBK" value="1"/>
											<el-option label="UTF-8" value="2"/>
										</el-select>
									</template>
								</el-table-column>
							</el-table>
						</div>
					</div>
					<!--<add ref="add"></add>-->
				</div>
			</el-form>
			<div class="dialog-footer">
				<el-button v-if="active>0" style="margin-top: 12px;" type="warning" @click="prev">上一步</el-button>
				<el-button v-if="active<2" style="margin-top: 12px;" type="primary" @click="next">下一步</el-button>
				<el-button v-if="active===2" style="margin-top: 12px;" type="success" @click="submitForm">完 成
				</el-button>
				<el-button style="margin-top: 12px;" type="info" @click="cancel">取 消</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import {listSystem, getPrincipalList, getSystemLayers,getTechDepts,addList} from "@/api/system/list";
import { listProject } from "@/api/project/project";

export default {
	name: "sysList",
	data() {
		return {
			show: true,
			active: 0,
			stepTitle: ['进行中', '已完成', '步骤 2', '步骤 3'],
			radio: '', // 如果使用单选框，定义一个model值
			activeName: 'mySystem',
			// 遮罩层
			loading: false,
			// 显示搜索条件
			showSearch: true,
			// 表格数据
			dataList: [],
			sysList: [
				{
					'sysId': 'CRPM',
					'sysName': '客户定价管理系统',
					'sysFloor': '产品服务层',
					'technicalDept': '管理信息组',
					'principal': 'j010贾璐',
					'status': '审核中'
				},
				{
					'sysId': 'IBSSFE',
					'sysName': '个人结售汇前后监测系统',
					'sysFloor': '产品服务层',
					'technicalDept': '管理信息组',
					'principal': 'j010贾璐',
					'status': '审核中'
				}
			],
			total: 0,
			// 弹出层标题
			title: "",
			// 是否显示弹出层
			open: false,
			// 查询参数
			queryParams: {
				pageNum: 1,
				pageSize: 10,
				managerId: undefined,
				sysStatus: undefined,
				connectToEsb: undefined,
				sysName: '',
				systemLayer: '',
				switchs: false
			},
			// 表单参数
			form: {},
			//控制提示信息
			useFlag: false,
			codeFlag: false,

			tableData: [],
			selectlistRow: [],
			rowNum: 1,
			useOptions: [],
			stateOptions: [],
			personOptions: [],//负责人
			sysLayerOptions: [],//所属层
			techDeptOptions:[],//技术部门
			projectOptions:[],

			// 表单校验
			rules: {
				sysCode: [
					{required: true, message: "系统编号不能为空", trigger: "blur"}
				],
				sysName: [
					{required: true, message: "系统名称不能为空", trigger: "blur"},
					{validator: this.checkName, trigger: "blur"},
				],
				systemLayer: [
					{required: true, message: "请选择系统所属层", trigger: "blur"}
				],
				timeOut:[
					{required: true, message: "请输入超时时间", trigger: "blur"},
				],
				areaName: [
					{required: true, message: "请选择区域名称", trigger: "blur"}
				],
				managerId: [
					{required: true, message: "请选择应用负责人", trigger: "blur"},
				],
				project: [
					{required: true, message: "请选择所属项目", trigger: "blur"},
				],
			},
		};
	},
	created() {
		this.getProjectList();
		this.getList();
		this.getPersonOptions();
		this.getSystemLayers();
		this.getTechDepts();
		this.getDicts("sys_statue").then(response => {
			this.stateOptions = response.data;
		});
	},
	methods: {
		//项目列表
		getProjectList(){
			listProject().then(res=>{
				this.projectOptions = res.rows
			})
		},
		//负责人列表
		getPersonOptions() {
			getPrincipalList().then(res => {
				this.personOptions = res.data
			})
		},
		getSystemLayers() {
			getSystemLayers().then(res => {
				this.sysLayerOptions = res.data
			})
		},
		//技术部门列表
		getTechDepts() {
			getTechDepts().then(res => {
				this.techDeptOptions = res.data
				console.log(res)
			})
		},

		//字典格式化
		stateFormatter(row, column) {
			return this.selectDictLabel(this.stateOptions, row.sysStatus);
		},
		checkName(rule, value, callback) {
			if (value) {
				if (/[\u4e00-\u9fa5]/g.test(value)) {
					callback();
				} else {
					callback(new Error('请输入中文!'));

				}
			}
			callback();
		},
		//关闭对话框
		close() {
			//步骤初始化
			this.active = 0
			//表单初始化
			this.tableData = []
		},

		//上一步
		prev() {
			this.active--
		},
		//下一步
		next() {
			//表单校验
			this.$refs["form1"].validate(valid => {
				if (valid) {
					this.active++;
					if (this.active > 2) this.active = 0;
				}
			});

		},

		handleSelectionChange(row) {
			if (row){
				this.radio = row.sysId;
			}
		},
		//点击行
		chooseOne(row) {
			if (row){
				this.radio = row.sysId;
			}
		},
		//表格样式
		cellStyle(row, column, rowIndex, columnIndex) {//根据报警级别显示颜色
			if (row.column.label === "系统名称") {
				return 'color:#49ba9c'
			}
		},
		//表格数据
		getList() {
			this.loading = true
			listSystem(this.queryParams).then(res => {
				this.sysList = res.rows
				this.total = res.total
				this.loading = false
			})

		},
		//点击标签页
		handleClick(tab) {
			if (tab.index === '0') {
				this.queryParams = {
					pageNum: 1,
					pageSize: 10,
					managerId: undefined,
					sysStatus: undefined,
					connectToEsb: undefined,
					sysName: '',
					systemLayer: '',
					switchs: false
				}
			}
			if (tab.index === '1') {
				this.queryParams = {
					pageNum: 1,
					pageSize: 10,
					managerId: undefined,
					sysStatus: undefined,
					connectToEsb: undefined,
					sysName: '',
					systemLayer: '',
					switchs: true
				}
			}
			this.handleQuery()
		},

		// 取消按钮
		cancel() {
			this.open = false;
		},
		/** 搜索按钮操作 */
		handleQuery() {
			this.getList();
		},
		/** 重置按钮操作 */
		resetQuery() {
			this.resetForm("queryForm");
			this.handleQuery();
		},
		/** 新增按钮操作 */
		handleAdd() {
			this.open = true;
			this.title = "系统新增";
		},


		/** 提交按钮 */
		submitForm: function () {
			this.$refs["form3"].validate(valid => {
				if (valid) {
					if (this.tableData.length > 0) {
						var flag = true;//定义一个变量控制验证信息
						this.tableData.forEach(item => {
							if (item.use === '' || item.code === '') {
								this.msgWarning("请选择用途/报文编码!");
								flag = false;
							}
						})
						//判断flag
						if (flag) {
							console.log(this.tableData)
							addList(this.form).then(res=>{
								this.msgSuccess("新增成功");
								this.open = false;
							}).then(()=>{
								this.getList();
							})
						}
					} else {
						this.msgWarning("请选择用途/报文编码!");
					}

				}
			});
		},
		/** 删除按钮操作 */
		handleDelete(row) {
			this.confirm('是否确认删除名称为"' + row.menuName + '"的数据项？').then(function () {
				return delMenu(row.menuId);
			}).then(() => {
				this.getList();
				this.$modal.msgSuccess("删除成功");
			}).catch(() => {
			});
		},

		getOptions() {
			this.useOptions = [];
		},
		// 获取表格选中时的数据
		selectRow(val) {
			this.selectlistRow = val
		},
		// 增加行
		addRow() {
			var list = {
				rowNum: this.rowNum,
				use: '',
				code: '',
			};
			this.tableData.unshift(list)
			this.rowNum += 1;
		},
		// 删除方法
		// 删除选中行
		delData() {
			for (let i = 0; i < this.selectlistRow.length; i++) {
				let val = this.selectlistRow
				// 获取选中行的索引的方法
				// 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
				// rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
				val.forEach((val, index) => {
					this.tableData.forEach((v, i) => {
						if (val.rowNum === v.rowNum) {
							// i 为选中的索引
							this.tableData.splice(i, 1)
						}
					})
				})
			}
			// 删除完数据之后清除勾选框
			this.$refs.table.clearSelection()
		}
	}
};
</script>
<style lang="scss">

.el-dialog__header {
	background: #13C2C2;
}

.el-dialog__title {
	color: #f2f2f2;
}

.tab-label {
	padding: 10px;
	font-size: 18px;
}

.item .el-form-item__label {
	margin: 10px auto;
	padding: 10px 0 0 10px;
	color: rgb(40, 40, 40);
	font-size: 20px;
}

.item .el-form--inline .el-form-item {
	vertical-align: center;
}

.cell-hover:hover {
	color: #2852a5;
}


.clearfix:before,
.clearfix:after {
	display: table;
	content: "";
}

.clearfix:after {
	clear: both
}

.title {
	color: rgb(40, 40, 40);
	font-size: 20px;

}

.box-card {
	width: 100%;
	margin-bottom: 30px;

	.el-card__header {
		background: #f6f1f1;
	}
}

.abow_dialog {
	display: flex;
	justify-content: center;
	align-items: Center;
	overflow: hidden;

	.el-dialog {
		margin: 0 auto !important;
		height: 95%;
		overflow: hidden;

		.el-dialog__body {
			position: absolute;
			left: 0;
			top: 54px;
			bottom: 0;
			right: 0;
			padding: 0;
			z-index: 1;
			overflow: hidden;
			overflow-y: auto;
		}

		.dialog-footer {
			text-align: center;
			margin-bottom: 10px;
		}
	}
}
</style>
