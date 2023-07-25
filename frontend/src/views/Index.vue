<template>
        <el-card class="box-card">
          <el-container>
            <el-main>
              <el-form ref="loginForm" :model="loginForm" :rules="myrules" label-width="100px">
                <el-form-item label="账号：" prop="phone">
                  <el-input v-model="loginForm.phone" placeholder="请输入" maxlength="11"/>
                </el-form-item>
                <el-form-item label="密码：" prop="password">
                  <el-input v-model="loginForm.password" type="password" placeholder="请输入"/>
                </el-form-item>
              </el-form>
              <div id="center">
              <el-radio-group v-model="identity" class="ml-4">
                <el-radio label="patient" size="large">用户</el-radio>
                <el-radio label="doctor" size="large">医生</el-radio>
              </el-radio-group>
              </div>
              <div id="center">
              <el-button type="primary" :loading="loginLoading" @click="onLogin">登录</el-button>
              <el-button @click="$router.push('/register')">注册</el-button>
              </div>
            </el-main>
          </el-container>
        </el-card>
</template>

<style scoped>
.box-card {
  margin-left:35%;
  margin-top:12%;
  width: 450px;
  height: 300px;
}
.el-input{
  margin-top:5px;
  margin-bottom:5px;
}
#center{
  margin: auto;
  margin-left: 30%;
  margin-top: 20px;
}
</style>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
  data() {
    return {
      loginForm:{
        phone: '',
        password: '',
      },
      identity: 'patient',
      isSuccess: false,
      myrules: {
        phone: [
            {required: true,message: "账号不能为空！",trigger: "change"},
            {pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,message: "请输入正确的手机号码！",trigger: "change"}
          ],
        password:[
            { required: true, message: '密码不能为空！', trigger: 'change' },
            { type: 'string', pattern: /^.{6,16}$/, message: '密码仅限6~16个字符！', trigger: 'change' }
          ],
        }
    }
  },
  methods: {
    onLogin () {
      this.$refs.loginForm.validate(valid => {
        if (!valid) {
          return false;
        }
        this.Login()
      })
    },
    Login() {
      if (this.identity === 'patient') {
        this.loginLoading = true;
        axios.get('http://8.130.117.156:8080/his/patient/login', {
          params: {
            phone: this.loginForm.phone,
            password: this.loginForm.password,
          }
        }).then(res => {
          this.isSuccess = res.data.success;
          this.loginLoading = false;
          if (!this.isSuccess) {
            ElMessage.error('账户不存在或密码错误');
          } else {
            let pInfo=res.data.patient;
            window.localStorage.setItem("pInfo",JSON.stringify(pInfo));
            this.$store.state.pInfo = pInfo;

            console.log(this.$store);
            this.$router.push('/patients');
          }
        }).catch(error => {
          ElMessage.error('请求出错，请检查API URL地址');
        });
      } else if (this.identity === 'doctor') {
        this.loginLoading = true;
        axios.get('http://8.130.117.156:8080/his/doctor/login', {
          params: {
            phone: this.loginForm.phone,
            password: this.loginForm.password,
          }
        }).then(res => {
          this.isSuccess = res.data.success;
          this.loginLoading = false;
          if (!this.isSuccess) {
            ElMessage.error('账户不存在或密码错误');
          } else {
            let dInfo=res.data.doctor;
            localStorage.setItem("dInfo",JSON.stringify(dInfo));
            this.$store.state.dInfo = dInfo;
            console.log(this.$store.state.dInfo);
            this.$router.push('/doctors');
          }
        }).catch(error => {
          console.error(error);
          ElMessage.error('请求出错，请检查API URL地址');
        });
      } else {
        ElMessage.error('账户不存在或密码错误');
      }
    }
  }
}
</script>