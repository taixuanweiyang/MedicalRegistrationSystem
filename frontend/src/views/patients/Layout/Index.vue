<template>
    <el-card class="box-card">
        <el-row>
            <el-row :span="10"><el-avatar :size="150" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" /></el-row>
            <el-row :span="20" class="text">{{ patientName }}, 欢迎使用HIS预约挂号系统!</el-row>
        </el-row> 
    </el-card>
    <el-main></el-main>
    <el-button type="primary" class="edit" @click="edit=false;">编辑个人资料</el-button>
    <el-card class="box-card">
        姓名：<el-input v-model="name" :disabled="edit" placeholder="请输入" />
        性别：<el-select v-model="sex" :disabled="edit" placeholder="请选择性别">
            <el-option label="男" value="male"></el-option>
            <el-option label="女" value="female"></el-option>
        </el-select>
        年龄：<el-input v-model="age" :disabled="edit" placeholder="请输入" /><div />
        <el-main></el-main>
        身份证号：<el-input v-model="id" disabled placeholder="请输入" />
        手机号：<el-input v-model="phone" :disabled="edit" placeholder="请输入" />
        <el-main><el-button type="primary" :disabled="edit" class="edit2" @click="modifyPatientInfo();edit=true;">保存</el-button></el-main>
        <el-tabs v-model="activeName" class="tabs" @tab-click="handleClick">
            <el-tab-pane label="挂号记录" name="Appointment">
                <el-table :data="tableData" style="width: 100%">
                  <el-table-column align="center" prop="patientId" label="患者Id" width="200" />
                  <el-table-column align="center" prop="doctorId" label="医生Id" width="200" />
                  <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
                  <el-table-column align="center" prop="registTime" label="挂号时间" width="200" />
                  <el-table-column align="center" prop="reserveTime" label="预约时间" width="170" />
                  <el-table-column align="center" prop="timeRange" label="上午/下午" width="150" :formatter="formatTimeRange"/>
                  <el-table-column align="center" prop="registDept" label="挂号科室" width="150" />
                  <el-table-column align="center" prop="medicalStatus" label="挂号状态" width="150" :formatter="formatMedicalStatus"/>
                  <el-table-column align="center" prop="operation" label="操作">
                    <template #default="{ row }">
                    <template v-if="row.medicalStatus === 0">
                      <el-button type="danger" text @click="cancelAppointment(row)">取消</el-button>
                    </template>
                    <template v-else>无</template>
                    </template>
                  </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="病历记录" name="Record">
                <el-table :data="tableData2" style="width: 100%">
                <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
                <el-table-column align="center" prop="time" label="病历填写时间" width="200" />
                <el-table-column align="center" prop="patientWord" label="主诉" width="230" />
                <el-table-column align="center" prop="dealAdvice" label="医生意见" width="230" />
                <el-table-column align="center" prop="medicalResult" label="诊断结果" width="230" />
                <el-table-column align="center" prop="doctorId" label="医生Id" width="230" />
            </el-table>
            </el-tab-pane>
        </el-tabs>
    </el-card>
</template>

<style scoped>
.box-card{
    width:100%
}
.el-row{
    margin-right:10px;
}
.text{
    margin-top:40px;
    font-size:25px;
}
.context{
    font-size:20px;
}
.edit{
    margin-left:88%;
    margin-bottom:20px;
}
.edit2{
    margin-left:93%;
}
.el-input{
    margin-left:10px;
    width: 300px;
}
</style>

<script>
import axios from "axios";
import{ElMessage}from 'element-plus'
import avatar from "@/assets/Avatar.png"
export default {
  data() {
    return {
      edit: true,
      patientName: this.$store.state.pInfo.name, 
      name: this.$store.state.pInfo.name, 
      sex: this.$store.state.pInfo.sex?'male':'female',
      age: this.$store.state.pInfo.age,
      id: this.$store.state.pInfo.id,
      phone: this.$store.state.pInfo.phone,
      avatar:avatar,
    };    
  },
  beforeCreate(){
    this.$store.state.pInfo=JSON.parse(window.localStorage.getItem("pInfo"));
  },
  created() {
    console.log("+++0" + this.$store.state.pInfo);
    console.log(this.patientName=this.$store.state.pInfo.name);
    this.fetchRegistrationInfo();
    this.fetchCaseInfo();
  },
  methods: {
    fetchRegistrationInfo() {
      
      axios
        .get("http://8.130.117.156:8080/his/patient/registration/info", {
          params: {
            patientId: this.$store.state.pInfo.id
          },
        })
        .then((res) => {
          this.tableData = res.data;
        })
        .catch((error) => {
          console.error("Error fetching registration info: ", error);
        });
    },
    fetchCaseInfo() {
      axios
        .get("http://8.130.117.156:8080/his/patient/case/info", {
          params: {
            patientId: this.$store.state.pInfo.id
          },
        })
        .then((res) => {
          this.tableData2 = res.data;
        })
        .catch((error) => {
          console.error("Error fetching case info: ", error);
        });
    },
    modifyPatientInfo() {
      const data = {
        id: this.$store.state.pInfo.id, 
        password:this.$store.state.pInfo.password,
        name: this.name,
        age: parseInt(this.age),
        sex: this.sex === "male",
        phone: this.phone,
      };
      axios
        .post("http://8.130.117.156:8080/his/patient/modify", data)
        .then((res) => {
          console.log(data);
          console.log(this.$store.state);
          this.$data.patientName=data.name;
          if (res.data) {
            this.$store.state.pInfo = data;
            ElMessage.success("修改信息成功！");
          } else {
            ElMessage.error("修改信息失败！");
          }
        })
        .catch((error) => {
          ElMessage.error("修改信息失败：" + error.message);
        });
    },
    cancelAppointment(row) {
    axios
      .get("http://8.130.117.156:8080/his/registration/cancel", {
        params: {
          patientNumber: row.patientNumber,
        },
      })
      .then((res) => {
        if (res.data) {
          row.medicalStatus = -1;
        } else {
          ElMessage.error("无法取消挂号");
        }
      })
      .catch((error) => {
        console.error("挂号取消失败: ", error);
      });
  },
  formatTimeRange(row) {
      return row.timeRange ? "下午" : "上午";
    },
    formatMedicalStatus(row) {
      if (row.medicalStatus === -1) {
        return "失效";
      } else if (row.medicalStatus === 0) {
        return "未就诊";
      } else {
        return "已就诊";
      }
    },
  },
};
</script>
