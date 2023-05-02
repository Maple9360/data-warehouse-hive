<template>
  <span v-if="title && title != 'title' && title != 'setting'">{{
    title
  }}</span>
  <img
    v-if="title === 'title'"
    style="width: 20vw"
    src="/src/assets/background/title.png"
    alt=""
  />
  <img
    v-if="title === 'setting'"
    style="width: 2vw"
    src="/src/assets/background/person.png"
    alt=""
  />
</template>

<script setup lang="ts">
const props = defineProps({
  icon: {
    type: String,
    default: '',
  },
  title: {
    type: String,
    default: '',
  },
})
</script>

<style lang="scss" scoped></style>
