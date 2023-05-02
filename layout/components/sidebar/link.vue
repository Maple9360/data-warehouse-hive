<template>
  <div>
    <component :is="type" v-bind="linkProps(to)">
      <slot></slot>
    </component>
  </div>
</template>

<script lang="ts" setup>
const props = defineProps({
  to: {
    type: String,
    required: true,
  },
  type: {
    value: 'router-link',
    default: 'router-link',
  },
})
const linkProps = (to) => {
  // 內部地址则设置router-link的to属性值，以及tag的属性为span
  return {
    to: to,
    tag: 'span',
  }
}
</script>

<style lang="scss" scoped>

</style>
