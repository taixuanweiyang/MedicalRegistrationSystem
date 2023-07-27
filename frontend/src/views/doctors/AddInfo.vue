<template>
    <el-card class="box-card">
        <el-row><strong>病历单</strong></el-row>
        <el-row>
            <el-col :span="8">就诊时间：<el-input v-model="time" placeholder="请输入" class="edit1"/></el-col>
            <el-col :span="8">就诊患者ID：<el-input v-model="patientId" placeholder="请输入" class="edit1"/></el-col>
            <el-col :span="8">接诊医生ID：<el-input v-model="doctorId" placeholder="请输入" class="edit1"/></el-col>
        </el-row>
        <el-row>患者自述：<el-input v-model="patientWord" placeholder="请输入" class="edit2"/></el-row>
        <el-row>诊断结果：<el-input v-model="dealAdvice" placeholder="请输入" class="edit2"/></el-row>
        <el-row>处理意见：<el-input v-model="medicalResult" placeholder="请输入" class="edit2"/></el-row>
        <el-row>是否需要处方<el-switch v-model="value1" class="btn"/></el-row>
    </el-card>
    <el-card v-if="value1==true" class="box-card">
        <el-row><strong>处方单</strong></el-row>
        <el-row>
            <el-col :span="8">开单时间ID：<el-input v-model="time" placeholder="请输入" class="edit1"/></el-col>
            <el-col :span="8">就诊患者ID：<el-input v-model="patientId" placeholder="请输入" class="edit1"/></el-col>
            <el-col :span="8">接诊医生：<el-input v-model="doctorId" placeholder="请输入" class="edit1"/></el-col>
        </el-row>
        <el-row>处方内容：<el-input v-model="content" placeholder="请输入" class="edit2"/></el-row>
    </el-card>
    <el-button type="primary" size="large" class="edit" @click="open">保存</el-button>
    <el-dialog
    v-model="dialogVisible"
    title="提示"
    width="30%"
    :before-close="handleClose">
    <span>提交成功</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false,$router.push('/doctors')">
          完成
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.box-card{
    width:100%;
    margin-bottom:20px;
}
.edit{
    margin-left:95%;
    margin-top:20px;
}
.el-row{
    margin-top:20px;
    margin-bottom:20px;
}
.btn{
    margin-left:20px;
}
.edit1{
    width:300px
}
.edit2{
    width:92%
}
</style>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      value1: false,
      dialogVisible: false,
      time: this.$moment(new Date()).format('YYYY-MM-DD HH:mm:ss'), 
      patientId: this.$store.state.registration.patientId,
      doctorId: this.$store.state.registration.doctorId,
      patientWord: "",
      dealAdvice: "", 
      medicalResult: "", 
      content: "",
      patientNumber: this.$store.state.registration.patientNumber,
    };
  },
  methods: {
    open() {
      this.submitMedicalRecord();
    },
    submitMedicalRecord() {
      const postData = {
        patientNumber: this.patientNumber,
        time: this.time,
        patientWord: this.patientWord,
        dealAdvice: this.dealAdvice,
        medicalResult: this.medicalResult,
        doctorId: this.$store.state.dInfo.id,
      };

      axios
        .post("http://8.130.117.156:8080/his/doctor/case/commit", postData)
        .then((res) => {
          if (res.data) {
            ElMessage.success("病历提交成功");
            if (this.value1) {
              this.submitPrescription();
            } else {
              this.dialogVisible = true;
            }
          } else {
            ElMessage.error("病历提交失败");
          }
        })
        .catch((error) => {
          ElMessage.error("病历提交失败：" + error.message);
        });
    },
    submitPrescription() {
      const postData = {
        patientNumber: this.patientNumber,
        time: this.time,
        content: this.content,
        doctorId: this.$store.state.dInfo.id,
      };

      axios
        .post("http://8.130.117.156:8080/his/doctor/prescription/commit", postData)
        .then((res) => {
          if (res.data) {
            ElMessage.success("处方提交成功");
          } else {
            ElMessage.error("处方提交失败");
          }
          this.dialogVisible = true;
        })
        .catch((error) => {
          ElMessage.error("处方提交失败：" + error.message);
          this.dialogVisible = true;
        });
    },
    handleClose(done) {
      this.time = "";
      this.patientWord = "";
      this.dealAdvice = "";
      this.medicalResult = "";
      this.content = "";
      this.patientNumber = "";
      done();
    },
  },
};
</script>

