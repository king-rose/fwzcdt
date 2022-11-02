<template>
    <div class="app-container">
        <el-radio-group v-model="label" style="margin-bottom: 30px;">
			<el-radio-button label="0">场景基本信息</el-radio-button>
            <el-radio-button label="1">场景指标</el-radio-button>
            <el-radio-button label="2">场景字段</el-radio-button>
            <el-radio-button label="3">变更详情</el-radio-button>
        </el-radio-group>
        <el-form :model="form_1" ref="form" :inline="true" label-width="100px" label-suffix=":" label-position="left" :rules="rules">
			<!--场景基本信息-->
			<div v-show="label==='0'">
				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="所属项目" required prop="projectCode">
							<el-select style="width:110%" v-model="form_1.projectCode">
								<el-option label="test" value=""/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="所属系统" required prop="scnSysId">
							<el-select style="width:110%" v-model="form_1.scnSysId">
								<el-option v-for="sys in systemOptions"
									:label="sys.sysName"
									:key="sys.sysId"
									:value="sys.sysId"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="接口" required prop="svInterface">
							<el-input style="width: 120%" v-model="form_1.svInterface"></el-input>
						</el-form-item>
					</el-col>

					<el-col :span="6">
						<el-form-item label="所属服务" required prop="serviceId">
							<el-select style="width:110%" v-model="form_1.serviceId">
								<el-option label="G01201002-项目管理" value="0"/>
								<el-option label="ZT08-广播服务" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>


				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="场景码" required class="code" prop="svCode">
							<el-input  v-model="form_1.svCode" placeholder="请输入场景码"/>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="场景名称" required class="code" prop="svName">
							<el-input v-model="form_1.svName" placeholder="请输入场景名称"/>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="场景类型" required prop="svType">
							<el-select style="width:110%" v-model="form_1.svType">
								<el-option label="服务场景" value="1"/>
								<el-option label="主题场景" value="2"/>
								<el-option label="组合服务场景" value="3"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="报文格式" required prop="svMessageFormat">
							<el-input style="width: 120%;" v-model="form_1.svMessageFormat"></el-input>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="通讯协议">
							<el-select style="width:110%" v-model="form_1.svCommunicationProtocol">
								<el-option label="TCP" value="TCP"/>
								<el-option label="HTTP" value="HTTP"/>
								<el-option label="HTTPS" value="HTTPS"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="HTTP-URL" class="code">
							<el-input v-model="form_1.svHttpUrl" placeholder="请输入url"/>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="是否适配">
							<el-select style="width:110%" v-model="form_1.jkAdapFlag">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="是否校验">
							<el-select style="width:110%" v-model="form_1.xmlValidFlag">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="是否对标">
							<el-select style="width:110%" v-model="form_1.stdFieldCheck">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="外部接口码" class="code">
							<el-input v-model="form_1.svExternalInterfaceCode" placeholder="请输入外部接口码"/>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="场景描述" class="code">
							<el-input maxlength="200"  show-word-limit type="textarea" v-model="form_1.scnDes" placeholder="请输入场景描述"/>
						</el-form-item>
					</el-col>
					<el-col :span="6"/>
				</el-row>
			</div>
			<!--变更详情-->
			<div v-show="label==='3'">
				<el-row type="flex" class="row-bg" justify="center">
					<el-col :span="8">
						<el-form-item label="变更内容" required class="code">
							<el-input type="textarea" v-model="form_1.updateContent" placeholder="请输入变更内容"/>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="变更原因" required class="code">
							<el-input type="textarea" v-model="form_1.updateReason" placeholder="请输入变更原因"/>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row style="float: right">
					<el-button type="primary">取消</el-button>
					<el-button type="primary" @click="submit">提交</el-button>
				</el-row>
			</div>
		</el-form>
		<el-form :model="form_2" ref="form" :inline="true" label-width="100px" label-suffix=":" label-position="left" :rules="rules">
			<!--场景指标-->
			<div v-show="label==='1'">
				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="服务窗口时间" class="code">
							<el-input v-model="form_2.sceneWindowTime" placeholder="请输入服务窗口时间" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="服务超时时间" class="code">
							<el-input v-model="form_2.sceneTimeout" placeholder="请输入服务超时时间" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="并发用户数" class="code">
							<el-input v-model="form_2.oneTimeUserNum" placeholder="请输入并发用户数" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="TPS每秒笔数" class="code">
							<el-input v-model="form_2.tradePerSecond" placeholder="请输入TPS每秒笔数" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="服务场景生效时间" class="code">
							<el-input v-model="form_2.onlineDate" placeholder="请输入服务窗口时间" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="服务场景失效时间" class="code">
							<el-input v-model="form_2.offlineDate" placeholder="请输入服务超时时间" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="优先级" class="code">
							<el-input v-model="form_2.scenePriority" placeholder="请输入优先级" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="是否开放场景">
							<el-select style="width:110%" v-model="form_2.isOpen">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="是否免费">
							<el-select style="width:110%" v-model="form_2.freeFlag">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="方法名称" class="code">
							<el-input v-model="form_2.methodName" placeholder="请输入方法名称" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="请求链接" class="code">
							<el-input v-model="form_2.requestUrl" placeholder="请输入请求链接" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="请求单价">
							<el-select style="width:110%" v-model="form_2.apiPrice">
								<el-option label="否" value="0"/>
								<el-option label="是" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row type="flex" class="row-bg" justify="space-around">
					<el-col :span="6">
						<el-form-item label="API类型" class="code">
							<el-input v-model="form_2.apiType" placeholder="请输入API类型" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="API版本号" class="code">
							<el-input v-model="form_2.apiVersion" placeholder="请输入API版本号" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="用户授权标识" class="code">
							<el-input v-model="form_2.authFlag" placeholder="请输入用户授权标识" />
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="HTTP方法">
							<el-select style="width:110%" v-model="form_2.httpMethod">
								<el-option label="POST" value="0"/>
								<el-option label="GET" value="1"/>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
			</div>
		</el-form>

		<!--场景字段-->
		<div v-show="label==='2'">
			<el-card class="box-card items mb20">
				<div slot="header" class="clearfix">
					<span class="title">场景输入字段</span>
					<el-button class="addButton" @click="add('In')" type="primary" size="small" plain>新增</el-button>
				</div>
				<div class="text item">
					<el-table ref="inTable" :data="fieldInList"  :row-style="{height:'20px'}" :cell-style="{padding:'10px'}" style="font-size: 14px" highlight-current-row>
						<el-table-column label="序号" align="center" min-width="5%">
							<template slot-scope="scope">
								{{scope.$index+1}}
							</template>
						</el-table-column>
						<el-table-column label="字段中文名" align="center" prop="sfCnName" min-width="7%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfCnName"/>
							</template>
						</el-table-column>
						<el-table-column label="字段英文名" align="center" prop="sfEnName" min-width="7%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfEnName"/>
							</template>
						</el-table-column>
						<el-table-column label="字段类型" align="center" prop="sfDataType" min-width="7%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfDataType==='1'? 'String':''"/>
							</template>
						</el-table-column>
						<el-table-column label="字段长度" align="center" prop="sfLength" min-width="7%">
							<template slot-scope="scope">
								<el-input-number style="width: 100px;" size="small" label="字段长度" :min="0" v-model="scope.row.sfLength"/>
							</template>
						</el-table-column>
						<el-table-column label="是否必输" align="center" prop="sfIsEnssential" min-width="7%">
							<template slot-scope="scope">
								<el-select v-model="scope.row.sfIsEnssential">
									<el-option label="非必输" value="0"/>
									<el-option label="必输" value="1"/>
								</el-select>
							</template>
						</el-table-column>
						<el-table-column label="是否敏感" align="center" prop="sfIsSensitive" min-width="7%">
							<template slot-scope="scope">
								<el-select v-model="scope.row.sfIsSensitive">
									<el-option label="非敏感" value="0"/>
									<el-option label="敏感" value="1"/>
								</el-select>
							</template>
						</el-table-column>

						<el-table-column label="默认值" align="center" prop="sfDefault" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfDefault"/>
							</template>
						</el-table-column>
						<el-table-column label="字段描述" align="center" prop="sfDes" min-width="8%">
							<template slot-scope="scope">
								<el-input type="textarea" v-model="scope.row.sfDes"/>
							</template>
						</el-table-column>

						<el-table-column label="枚举值" align="center" prop="sfEnum" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfEnum"/>
							</template>
						</el-table-column>

						<el-table-column label="循环次数" align="center" prop="sfCycleTimes" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfCycleTimes"/>
							</template>
						</el-table-column>
						<el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="7%">
							<template slot-scope="scope">
								<el-button
									size="mini"
									type="text"
									icon="el-icon-delete"
									@click="deleteRow('in',scope.$index)"
								>删除
								</el-button>
							</template>
						</el-table-column>
					</el-table>
					<pagination
						v-show="fieldInParams.total>0"
						:total="fieldInParams.total"
						:page.sync="fieldInParams.pageNum"
						:limit.sync="fieldInParams.pageSize"
						@pagination="getFieldInList"
					/>
				</div>
			</el-card>

			<el-card class="box-card items mb20">
				<div slot="header" class="clearfix">
					<span class="title">场景输出字段</span>
					<el-button class="addButton" @click="add('Out')" type="primary" size="small" plain>新增</el-button>
				</div>
				<div class="text item">
					<el-table ref="inTable" :data="fieldOutList" :row-style="{height:'20px'}" :cell-style="{padding:'10px'}"
							  style="font-size: 14px" highlight-current-row :row-class-name="tableRowClassName">
						<el-table-column label="序号" align="center" min-width="5%">
							<template slot-scope="scope">
								{{scope.$index+1}}
							</template>
						</el-table-column>
						<el-table-column label="字段中文名" align="center" prop="sfCnName" min-width="8%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfCnName"/>
							</template>
						</el-table-column>
						<el-table-column label="字段英文名" align="center" prop="sfEnName" min-width="8%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfEnName"/>
							</template>
						</el-table-column>
						<el-table-column label="数据类型" align="center" prop="sfDataType" min-width="7%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfDataType==='1'? 'String':''"/>
							</template>
						</el-table-column>
						<el-table-column label="字段长度" align="center" prop="sfLength" min-width="7%">
							<template slot-scope="scope">
								<el-input-number style="width: 100px;" size="small" label="字段长度" :min="0" v-model="scope.row.sfLength"/>
							</template>
						</el-table-column>
						<el-table-column label="小数位" align="center" prop="sfFraction" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfFraction"/>
							</template>
						</el-table-column>
						<el-table-column label="默认值" align="center" prop="sfDefault" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfDefault"/>
							</template>
						</el-table-column>
						<el-table-column label="是否必输" align="center" prop="sfIsEnssential" min-width="6%">
							<template slot-scope="scope">
								<el-select v-model="scope.row.sfIsEnssential">
									<el-option label="非必输" value="0"/>
									<el-option label="必输" value="1"/>
								</el-select>
							</template>
						</el-table-column>
						<el-table-column label="枚举列表" align="center" prop="sfEnum" min-width="5%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfEnum"/>
							</template>
						</el-table-column>
						<el-table-column label="字段位置" align="center" prop="sfPath" min-width="10%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfPath"/>
							</template>
						</el-table-column>
						<el-table-column label="备注" align="center" prop="sfDes" min-width="10%">
							<template slot-scope="scope">
								<el-input v-model="scope.row.sfDes"/>
							</template>
						</el-table-column>
						<el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="10%">
							<template slot-scope="scope">
								<el-button
									size="mini"
									type="text"
									icon="el-icon-delete"
									@click="deleteRow('out',scope.$index)"
								>删除
								</el-button>
							</template>
						</el-table-column>
					</el-table>
					<pagination
						v-show="fieldOutParams.total>0"
						:total="fieldOutParams.total"
						:page.sync="fieldOutParams.pageNum"
						:limit.sync="fieldOutParams.pageSize"
						@pagination="getFieldOutList"
					/>
				</div>
			</el-card>
		</div>



            <!--接口详情-->
			<!--            <div v-show="radioList.label==='3'">
                <el-card class="card_one">
                    <div slot="header">
                        <span>接口信息</span>
                    </div>
                    <div slot="default" style="text-align: center;">
                        <el-row :gutter="24" style="margin-bottom: 15px">
                            <el-col :span="2">
                                <span style="line-height: 33px">接口码</span>
                            </el-col>
                            <el-col :span="10">
                                <el-input></el-input>
                            </el-col>
                            <el-col :span="2">
                                <span  style="line-height: 33px">接口名</span>
                            </el-col>
                            <el-col :span="10">
                                <el-input></el-input>
                            </el-col>
                        </el-row>

                        <el-row :gutter="24" style="margin-bottom: 15px">
                            <el-col :span="2">
                                <span style="line-height: 33px">报文类型</span>
                            </el-col>
                            <el-col :span="10">
                                <el-select style="width: 100%" v-model="form.type">
                                    <el-option label="0-xml" value="0"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="2">
                                <span  style="line-height: 33px">接口描述</span>
                            </el-col>
                            <el-col :span="10">
                                <el-input type="textarea"></el-input>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <el-card>
                    <div slot="header" class="card_two">
                        接口输入字段
                    </div>

                    <div slot="default">
                        <el-row :gutter="10" class="mb8">
                            <el-col :span="1.5">
                                <el-button type="primary" @click="handleAdd" >添加字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="warning" @click="handleAdd">修改字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="danger" @click="handleAdd">删除字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="success" @click="handleAdd">上移字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="info" @click="handleAdd">下移字段</el-button>
                            </el-col>
                        </el-row>

                        <el-table ref="inteface-in" :data="interfaceInList">
                            <el-table-column label="字段英文名" prop=""/>
                            <el-table-column label="字段中文名" prop=""/>
                            <el-table-column label="字段类型" prop=""/>
                            <el-table-column label="是否必输" prop=""/>
                            <el-table-column label="是否敏感字段" prop=""/>
                            <el-table-column label="字段长度" prop=""/>
                            <el-table-column label="默认值" prop=""/>
                            <el-table-column label="描述" prop=""/>
                            <el-table-column label="枚举值" prop=""/>
                            <el-table-column label="循环次数" prop=""/>
                            <el-table-column label="接口字段位置" prop=""/>
                            <el-table-column label="命名空间" prop=""/>
                        </el-table>
                    </div>
                </el-card>

                <el-card>
                    <div slot="header" class="card_two">
                        接口输出字段
                    </div>

                    <div slot="default">
                        <el-row :gutter="10" class="mb8">
                            <el-col :span="1.5">
                                <el-button type="primary" @click="handleAdd" >添加字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="warning" @click="handleAdd">修改字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="danger" @click="handleAdd">删除字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="success" @click="handleAdd">上移字段</el-button>
                            </el-col>

                            <el-col :span="1.5">
                                <el-button type="info" @click="handleAdd">下移字段</el-button>
                            </el-col>
                        </el-row>

                        <el-table ref="inteface-in" :data="interfaceInList">
                            <el-table-column label="字段英文名" prop=""/>
                            <el-table-column label="字段中文名" prop=""/>
                            <el-table-column label="字段类型" prop=""/>
                            <el-table-column label="是否必输" prop=""/>
                            <el-table-column label="是否敏感字段" prop=""/>
                            <el-table-column label="字段长度" prop=""/>
                            <el-table-column label="默认值" prop=""/>
                            <el-table-column label="描述" prop=""/>
                            <el-table-column label="枚举值" prop=""/>
                            <el-table-column label="循环次数" prop=""/>
                            <el-table-column label="接口字段位置" prop=""/>
                            <el-table-column label="命名空间" prop=""/>
                        </el-table>
                    </div>
                </el-card>
            </div>-->



        <!--场景字段-->
<!--        <el-dialog width="40%" title="字段详情编辑" :visible.sync="open">
            <el-form :model="form_3" :inline="true" label-width="100px" label-position="left">
                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="英文名称" required class="code">
                            <el-input v-model="form_3.enName" placeholder="请输入字段英文名"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="中文名称" required class="code">
                            <el-input  v-model="form_3.znName" placeholder="请输入字段中文名"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="数据类型" required>
                            <el-select v-model="form_3.type">
                                <el-option label="字符串" value="0"/>
                                <el-option label="整型" value="1"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否必输" >
                            <el-switch
                                v-model="form_3.isEmpty"
                                active-color="#13ce66"
                                active-text="是"
                                active-value="1"
                                inactive-text="否"
                                inactive-value="0"
                                inactive-color="#ececec">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="字段长度" class="code">
                            <el-input  v-model="form_3.length" placeholder="请输入字段长度"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否敏感字段" >
                            <el-select v-model="form_3.isMG">
                                <el-option label="否" value="0"/>
                                <el-option label="是" value="1"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="默认值" class="code">
                            <el-input  v-model="form_3.default" placeholder="请输入默认值"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="循环次数" class="code">
                            <el-input  v-model="form_3.times" placeholder="请输入循环次数"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="HTTP字段位置">
                            <el-select v-model="form_3.isMG">
                                <el-option label="json-body" value="0"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="加密模式">
                            <el-select v-model="form_3.isMG">
                                <el-option label="不加密" value="0"/>
                                <el-option label="加密" value="1"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="枚举列表" class="code">
                            <el-input type="textarea" maxlength="200" show-word-limit v-model="form_3.list" placeholder="请输入枚举列表"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="填写示例" class="code">
                            <el-input type="textarea" maxlength="200" show-word-limit v-model="form_3.list" placeholder="请输入填写示例"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="备注" class="code">
                            <el-input type="textarea" maxlength="200" show-word-limit v-model="form_3.remark" placeholder="请输入备注"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12"></el-col>
                </el-row>
            </el-form>

        </el-dialog>-->
        <!--接口字段-->
<!--        <el-dialog width="40%" title="接口字段编辑" :visible.sync="jkOpen">
            <el-form :model="form" :inline="true" label-width="100px" label-position="left">
                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="英文名称" required class="code">
                            <el-input v-model="form.enName" placeholder="请输入字段英文名"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="中文名称" required class="code">
                            <el-input  v-model="form.znName" placeholder="请输入字段中文名"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="字段路径" required class="code">
                            <el-input  v-model="form.znName" placeholder="请输入字段路径"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="字段类型" required>
                            <el-select v-model="form.type">
                                <el-option label="字符串" value="0"/>
                                <el-option label="整型" value="1"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="是否敏感" >
                            <el-switch
                                v-model="form.path"
                                active-color="#13ce66"
                                active-text="是"
                                active-value="1"
                                inactive-text="否"
                                inactive-value="0"
                                inactive-color="#ececec">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否必输" >
                            <el-switch
                                v-model="form.path"
                                active-color="#13ce66"
                                active-text="是"
                                active-value="1"
                                inactive-text="否"
                                inactive-value="0"
                                inactive-color="#ececec">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="字段长度" class="code">
                            <el-input  v-model="form.length" placeholder="请输入字段长度"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="默认值" class="code">
                            <el-input  v-model="form.default" placeholder="请输入默认值"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="循环次数" class="code">
                            <el-input  v-model="form.times" placeholder="请输入循环次数"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="命名空间" class="code">
                            <el-input  v-model="form.times"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row type="flex" class="row-bg" justify="center">
                    <el-col :span="12">
                        <el-form-item label="字段枚举值" class="code">
                            <el-input type="textarea" maxlength="200" show-word-limit v-model="form.list" placeholder="请输入枚举列表"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="备注" class="code">
                            <el-input type="textarea" maxlength="200" show-word-limit v-model="form.list" placeholder="请输入填写示例"/>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitFileForm">确 定</el-button>
                <el-button @click="jkOpen = false">取 消</el-button>
            </div>
        </el-dialog>-->
    </div>
</template>

<script>

    import {sceneBasicInfo,getSceneQuotaInfo,getSceneFiledIn,getSceneFiledOut,sceneEdit,quatoEdit,fieldEdit} from "@/api/service/oprscenario";
	import {listSystem} from "@/api/system/list";
	import request from '@/utils/request'

    export default {
        name: "serviceAdd",
        components: {},
        data() {
            return {
				svId:'',
				svCode:'',
				label: '0',
				//遮罩层
				inLoading:false,
				outLoading:false,

				//查询参数
				fieldInParams:{
					pageNum:1,
					pageSize:10,
					total:0,
					svId:'',
					type:'0'
				},
				fieldOutParams:{
					pageNum:1,
					pageSize:10,
					total:0,
					svId:'',
					type:'1'
				},

                //表单参数
				form_1:{},
				form_2:{},
				//检验
				rules:{
					projectCode:[
						{required: true, message: "项目不能为空", trigger: "blur"},
					],
					scnSysId:[
						{required: true, message: "系统不能为空", trigger: "blur"},
					],
					svInterface:[
						{required: true, message: "接口不能为空", trigger: "blur"},
					],
					serviceId:[
						{required: true, message: "所属服务不能为空", trigger: "blur"},
					],
					svCode:[
						{required: true, message: "场景码不能为空", trigger: "blur"},
					],
					svName:[
						{required: true, message: "场景名称不能为空", trigger: "blur"},
					],
					svType:[
						{required: true, message: "场景类型不能为空", trigger: "blur"},
					],
					svMessageFormat:[
						{required: true, message: "报文格式不能为空", trigger: "blur"},
					]
				},

				//场景字段数据
                fieldInList:[],
				fieldOutList:[],

				systemOptions:[],

                tempRoute: {},
                //遮罩层
                open:false,
            };
        },

        created() {
			this.svId = this.$route.query.svId
			this.getSystemList();
			this.getSceneInfo()

        },
		activated() {
			this.tempRoute = Object.assign({}, this.$route)
			this.setTagsViewTitle()
		},
		methods: {
			//重置
			reset(){
				this.form_1 = {}
				this.form_2 = {}
				this.label = '0'
			},

			//选项卡标题
            setTagsViewTitle() {
                const title = '服务场景编辑'
                const route = Object.assign({}, this.tempRoute, {title: `${title}`})
                this.$store.dispatch('tagsView/updateVisitedView', route)
            },
            //增加一行
            add(type){
				if (type==='In'){
					if (this.fieldInList == undefined) {
						this.fieldInList = new Array();
					}
					let obj = {};
					obj.scnCode = this.svCode;
					obj.sfCnName = "";
					obj.sfEnName = "";
					obj.sfType = '0';
					obj.sfDataType = '1';
					obj.sfLength = '0';
					this.fieldInList.push(obj);
				}
				if (type==='Out'){
					if (this.fieldOutList == undefined) {
						this.fieldOutList = new Array();
					}
					let obj = {};
					obj.scnCode = this.svCode;
					obj.sfCnName = "";
					obj.sfEnName = "";
					obj.sfType = '1';
					obj.sfDataType = '1';
					obj.sfLength = '0';
					this.fieldOutList.push(obj);
				}
            },
			// 删除一行
			deleteRow(type,index) {
				const indexNum = index+1
				this.$confirm('是否删除第"' + indexNum + '"行数据?', "警告", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(res=>{
					if (type==='in'){
						this.fieldInList.splice(index, 1)
					}
					if (type==='out'){
						this.fieldOutList.splice(index, 1)
					}
				})

			},

			tableRowClassName({row,rowIndex}) {
				row.index = rowIndex; //在原有的数据上加一个index
			},
			getSystemList(){
				listSystem().then(res=>{
					this.systemOptions = res.rows
				})
			},

            //场景关联信息
			getSceneInfo(){
				this.reset();
                sceneBasicInfo(this.svId).then(res=>{
					this.form_1 = res.data;
					this.svCode = res.data.svCode;
                })
				getSceneQuotaInfo(this.svId).then(res=>{
					if (res.data){
						this.form_2 = res.data;
					}
				})
				this.getFieldInList();
				this.getFieldOutList();
            },
			//场景字段输入
			getFieldInList(){
				this.inLoading = true;
				//场景字段输入
				this.fieldInParams.svId = this.svId;
				getSceneFiledIn(this.fieldInParams).then(res=>{
					this.fieldInList = res.rows
					this.fieldInParams.total = res.total
					this.inLoading = false;
				})
			},
			//场景字段输出
			getFieldOutList(){
				this.outLoading = true;
				//场景字段输出
				this.fieldOutParams.svId = this.svId;
				getSceneFiledOut(this.fieldOutParams).then(res=>{
					this.fieldOutList = res.rows
					this.fieldOutParams.total = res.total
					this.outLoading = false;
				})
			},
			//提交修改
			submit(){
				this.$refs['form'].validate(validate=>{
					if (validate){
						//更新场景
						sceneEdit(this.form_1).then(res=>{
							console.log(res)
						}).then(()=>{
							//更新场景指标
							this.form_2.sceneVersionId = this.svId
							quatoEdit(this.form_2).then(res=>{
								console.log(res)
							})
						}).then(()=>{
							//更新场景字段
							const  svId= this.svId;
							let data={
								'formData':JSON.stringify(this.fieldInList.concat(this.fieldOutList)),
							}
							this.$confirm('是否提交ID为"' +  svId + '"的申请单据?', "警告", {
								confirmButtonText: "确定",
								cancelButtonText: "取消",
								type: "warning"
							}).then(function () {
								return request({
									url: 'oprScene/sceneFieldEdit/'+svId,
									method: 'get',
									params: data
								});
							}).then(()=>{
								this.$emit('getList');
								this.msgSuccess("更新成功");
								this.$store.dispatch('tagsView/delView', this.$route).then(() => {
									this.$router.push({
										path: '/service/list'
									});
								});
							}).catch(function (e) {
								if (e === 'cancel'){
									this.msgWarning("已取消");
									return;
								}
								this.msgError("更新失败!:"+e);
							});
						})
					}
				})
			}

        }
    };
</script>
<style lang="scss">
    .code{
        .el-input{
            width: 218px;
        }
        .el-textarea{
            width: 218px;
        }
    }
    /**按钮样式*/
    .addButton{
        float: right;
        position: relative;
        z-index: 1;
        transform: translateY(-5px);
    }
    .card_one{
        font-size: 18px;
    }

    .card_two{
        padding: 5px;
        text-align: center;
        font-size: 20px;
        font-weight: 500
    }
</style>
