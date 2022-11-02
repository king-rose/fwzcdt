<template>
  <div class="app-container">
    <el-container>
      <el-header>场景指标信息</el-header>
      <el-main>
        <el-row>
          <el-col :span="8">
            是否微服务系统：传统系统
          </el-col>
          <el-col :span="8">
            系统编号：tz001
          </el-col>
          <el-col :span="8">
            系统名称：测首
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            系统描述：测试
          </el-col>
          <el-col :span="8">
            系统所属层：产品及业务处理层
          </el-col>
          <el-col :span="8">
            开发商：测试
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            系统上线日期：2022-04-10
          </el-col>
          <el-col :span="8">
            系统下线日期：2022-10-03
          </el-col>
          <el-col :span="8">
            超时时间(ms)：60000
          </el-col>
         </el-row>
        <el-row>
          <el-col :span="8">
            用于(字符集)：提供方(GB18030)
          </el-col>
          <el-col :span="8">
            渠道编码：cs_qdbm
          </el-col>
          <el-col :span="8">
            是否已接入ESB：是
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            报文类型：测试报文
          </el-col>
          <el-col :span="8">
            通讯协议：测试协议
          </el-col>
          <el-col :span="8">
            审核状态：审核中
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            集成方式：测试
          </el-col>
          <el-col :span="8">
            鉴权类型：不鉴权
          </el-col>
          <el-col :span="8">
            创建时间：2022-02-21 17:53:08
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            修改时间：2022-03-31
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-container>
      <el-header>安全信息</el-header>
      <el-main>
        <el-row>
          <el-col :span="8">
            灾备等级：测试
          </el-col>
          <el-col :span="8">
            灾备模式：测试
          </el-col>
          <el-col :span="8">
            灾备恢复时间目标：2022-04-01
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            灾备恢复点目标RPO(分钟)：200
          </el-col>
          <el-col :span="8">
            灾备系统情况说明：测试
          </el-col>
          <el-col :span="8">
            安全保护等级：测试
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            是否建立应急预案：是
          </el-col>
          <el-col :span="8">
            是否已纳入监控：是
          </el-col>
          <el-col :span="8">
            系统密码：测试
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-container>
      <el-header>服务信息</el-header>
      <el-main>
        <el-row>
          <el-col :span="8">
            服务时间：2020-04-01
          </el-col>
          <el-col :span="8">
            使用用户：系统管理员
          </el-col>
          <el-col :span="8">
            服务范围：行内
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            是否直面客户：是
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-container>
      <el-header>基本技术信息</el-header>
      <el-main>
        <el-row>
          <el-col :span="8">
            系统类型：测试
          </el-col>
          <el-col :span="8">
            访问方式：测试
          </el-col>
          <el-col :span="8">
            兼容浏览器：测试
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            应用服务器高可用技术：测试
          </el-col>
          <el-col :span="8">
            应用服务器是否负载均衡：测试
          </el-col>
          <el-col :span="8">
            操作系统：测试
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            数据库：oracle
          </el-col>
          <el-col :span="8">
            中间件：测试
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-container>
      <el-header>配置项目</el-header>
      <el-main>
        <el-row>
          <el-col :span="8">
            所属项目：LZHPOC
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "essential-information",
        data(){
          return{}
        },
        created() {
        },
        methods:{},
    }
</script>

<style scoped>
  .el-header{
    background-color: #f7f7f7;
    border: solid 1px #e2e2e2;
    font-size: 20px;
    border-bottom:none;
    line-height: 60px;
  }
  .el-main {
    font-size: 15px;
    border: solid 1px #e2e2e2;
  }
</style>
