export const debounce = (fn: () => void) => {
  console.log('debounce')
  let timer = null
  return function () {
    if (timer) {
      clearTimeout(timer)
    }
    timer = setTimeout(() => {
      fn()
    }, 1000)
  }
}
