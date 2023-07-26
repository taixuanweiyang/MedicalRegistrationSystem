<template>
    <router-link to="/doctors"><el-avatar :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" /></router-link>
</template>

<script>
import avatar from "@/assets/Avatar.png"
export default {
  data(){
                return {
                    avatar:avatar,
                }

        },
}
</script>