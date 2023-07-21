<template>
  <el-carousel :interval="4000" type="card" height="650px">
    <el-carousel-item v-for="item in imgs" :key="item">
      <el-image style="height: 100%" :src="item" fit="fill" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import img1 from '@/assets/test1.png'
import img2 from '@/assets/test2.png'
import img3 from '@/assets/test3.png'

export default {
    name: 'TestCarousel',
    data() {
        return {
            imgs: [
                img1,
                img2,
                img3
            ]
        }
    },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
