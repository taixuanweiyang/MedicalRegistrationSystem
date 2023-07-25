<template>
    <router-link to="/doctors"><el-avatar :size="50" :src="avatar" /></router-link>
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