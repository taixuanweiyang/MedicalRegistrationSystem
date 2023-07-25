<template>
    <el-card class="box-card">
        <el-row>
            <el-row :span="10"><el-avatar :size="150" :src="avatar" /></el-row>
            <el-row :span="20">
                <el-col class="text">现为{{doctorName}}用户使用HIS后台管理系统</el-col>
                <el-col class="text">您有{{number}}条预约记录等待被处理</el-col>
            </el-row>
        </el-row> 
    </el-card>
    <el-main></el-main>
    <el-card class="box-card">
        <el-col class="context"><strong>待处理预约记录</strong></el-col>
        <el-divider />
        <el-table :data="tableData" style="width: 100%">
            <el-table-column align="center" label="序号" type="index" width="90" />
            <el-table-column align="center" prop="patientId" label="患者Id" width="200" />
            <el-table-column align="center" prop="doctorId" label="医生Id" width="200" />
            <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
            <el-table-column align="center" prop="registTime" label="挂号时间" width="200" />
            <el-table-column align="center" prop="reserveTime" label="预约时间" width="170" />
            <el-table-column align="center" prop="timeRange" label="上午/下午" width="170" :formatter="formatTimeRange"/>
            <el-table-column align="center" prop="registDept" label="挂号科室" width="170" />
            <el-table-column align="center" prop="medicalStatus" label="挂号状态" width="170" :formatter="formatMedicalStatus"/>
            <el-table-column align="center" prop="operation" label="操作" width="200">
              <template #default="{ row }">
                <template v-if="row.medicalStatus === 0">
                  <el-button type="success" text @click="handleStartTreatment(row)">开始就诊</el-button>
                  <el-button type="danger" text @click="handleInvalidRegistration(row)">失效</el-button>
              </template>
              <template v-else>无</template>
             </template>
            </el-table-column>
        </el-table>
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
    margin-top:25px;
    font-size:25px;
}
</style>


<script>
import axios from "axios";
import { ElMessage } from "element-plus";
import avatar from "@/assets/Avatar.png";

export default {
  data() {
    return {
      avatar: avatar,
      doctorName: this.$store.state.dInfo.name, 
      tableData: [], 
      number:'',
    };
  },
  beforeCreate(){
    this.$store.state.dInfo=JSON.parse(window.localStorage.getItem("dInfo"));
  },
  created() {
    this.fetchPendingAppointments();
  },
  methods: {
    fetchPendingAppointments() {
      axios.get("http://8.130.117.156:8080/his/doctor/registration/specificinfo", {
          params: {
            doctorId: this.$store.state.dInfo.id, 
          },
        })
        .then((res) => {
          this.tableData = res.data;
          this.number=this.tableData.length;
        })
        .catch((error) => {
          ElMessage.error("获取待处理预约记录失败：" + error.message);
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
    handleStartTreatment(row) {
      const postData = {
        patientId: row.patientId,
        doctorId: row.doctorId,
        time: this.$moment(new Date()).format('YYYY-MM-DD hh:mm:ss'),
      };
      axios
        .post("http://8.130.117.156:8080/his/doctor/medicalrecord/commit", postData)
        .then((res) => {
          if (res.data) {
            ElMessage.success("开始诊治");
            this.$store.state.registration=row;
            this.$router.push('/doctors/addInfo');
          } else {
            ElMessage.error("就诊失败");
          }
        })
        .catch((error) => {
          ElMessage.error("就诊记录提交失败：" + error.message);
        });
    },
    handleInvalidRegistration(row) {
      axios
        .get("http://8.130.117.156:8080/his/registration/cancel", {
          params: {
            patientNumber: row.patientNumber,
          },
        })
        .then((res) => {
          if (res.data) {
            row.medicalStatus = -1;
            ElMessage.success("挂号失效请求成功");
          } else {
            ElMessage.error("挂号失效请求失败");
          }
        })
        .catch((error) => {
          ElMessage.error("挂号失效请求失败：" + error.message);
        });
    },
  },
}
</script>