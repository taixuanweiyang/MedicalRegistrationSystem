<template>
  <div>
    <el-main>
      <el-row>
        <el-card class="box-card" v-for="(doctor, index) in doctors" :key="index">
          <template #header>
            <div class="card-header">
              <span>{{ doctor.name }}</span>
            </div>
          </template>
          <div>所属科室：{{ doctor.dept }}</div>
          <div>性别：{{ doctor.sex ? "男" : "女" }}</div>
          <div>手机号：{{ doctor.phone }}</div>
          <div>挂号费：{{ doctor.fee }}</div>
          <div>个人介绍：{{ doctor.introduction }}</div>
          <el-divider />
          <el-row>是否预约该医生？<el-switch class="btn" v-model="doctor.value" :before-change="select.bind(this, index)"/></el-row>
          <el-date-picker
            v-if="doctor.value==true"
            v-model="doctor.reserveDate"
            type="date"
            placeholder="请选择日期"
            :size="large"
          />
          <el-button type="primary" v-if="doctor.value==true" class="picker" @click="timeRange=false">
            <div>上午</div><br />
            <div>{{ reserveCount }}/10</div>
          </el-button>
          <el-button type="primary" v-if="doctor.value==true" class="picker" @click="timeRange=true">
            <div>下午</div><br />
            <div>{{ reserveCount }}/10</div>
          </el-button>
          <el-button type="primary" v-if="doctor.value==true"  class="picker2" @click="reserveAppointment">确定预约并支付</el-button>
        </el-card>
      </el-row>
    </el-main>
  </div>
  <el-dialog
    v-model="dialogVisible"
    title="提示"
    width="30%"
    :before-close="handleClose">
    <span>支付成功</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false,$router.push('/patients/Center')">
          完成
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>


<style scoped>
.box-card{
  margin-top:5%;
  margin-right:10px;
  height:400px;
  width:300px;
  background-color:#E8F7FF;
}
.picker{
  margin-top:10px;
  margin-bottom:10px;
}
.picker2{
  margin-left:10px;
}
</style>

<script>
import axios from "axios";
import{ElMessage}from 'element-plus'
export default {
  data() {
    return {
      doctors: [], 
      value:false,
      timeRange: false,
      reserveCount: 0,
      idx: -1,
      dialogVisible:false,
    };
  },
  created() {
    this.fetchDoctorInfo();
    this.fetchReserveCount();
  },
  methods: {
    fetchDoctorInfo() {
      axios
        .get("http://8.130.117.156:8080/his/doctors", {
          params: {
            dept: this.$store.state.dept,
          },
        })
        .then((res) => {
          this.doctors = res.data;
        })
        .catch((error) => {
          console.error("Failed to fetch doctor info: ", error);
        });
    },
    fetchReserveCount() {
      axios
        .get("http://8.130.117.156:8080/his/doctor/reserve/info", {
          params: {
            doctorId: this.doctors.id,
            date: this.doctors.reserveDate,
            timeRange: this.doctors.timeRange,
          },
        })
        .then((res) => {
          this.reserveCount = res.data;
        })
        .catch((error) => {
          console.error("Failed to fetch reserve count: ", error);
        });
    },
    reserveAppointment() {
      const selectedDoctor = this.doctors.find((doctor) => doctor.value === true);

      if (selectedDoctor && this.reserveCount < 10) {
        const requestData = {
          registTime:this.$moment(new Date()).format('YYYY-MM-DD hh:mm:ss'),
          reserveTime: this.$moment(selectedDoctor.reserveDate).format('YYYY-MM-DD'),
          timeRange: this.timeRange,
          registDept: this.$store.state.dept,
          doctorId: this.doctors[this.$data.idx].id,
          patientId: this.$store.state.pInfo.id,
          medicalStatus: 0,
        };
        console.log(requestData);

        axios
          .post("http://8.130.117.156:8080/his/registration/commit", requestData)
          .then((res) => {
            if (res.data) {
              this.dialogVisible = true;
            } else {
              ElMessage.error("无法预约");
            }
          })
          .catch((error) => {
            console.error("预约失败: ", error);
          });
      } else {
        console.log("预约已满");
      }
    },
    select(index){
      if(this.$data.idx == -1) {
        this.$data.idx = index;
        return true;
      }
      else if(index === this.$data.idx) {
        this.$data.idx = -1;
        return true;
      }
      else {
        return false;
      }
    },
    
  },
};
</script>