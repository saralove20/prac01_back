<script setup>
import axios from 'axios'

const subscribePush = async () => {
  const permission = await Notification.requestPermission()
  if(permission !== 'granted') {
    console.log("권한 없음")

    return
  }

  await navigator.serviceWorker.register('/service-worker.js')
  const VAPID_PUBLIC_KEY = "BLuE9qvTS5SEuxixrR9ra9PP4Q1nkAfpZ4xBHUkMlw2cNgIFapUXt1IyJeSopWnwEC64FRP0uESlf1Tvntf6e4w"

  const registration = await navigator.serviceWorker.ready;
  let subscription = await registration.pushManager.getSubscription()

  // 구독 상태를 확인하고 구독이 안되어 있으면 구독을 하게 설정
  if(!subscription) {
    subscription = await registration.pushManager.subscribe( {
      userVisibleOnly: true,
      applicationServerKey: VAPID_PUBLIC_KEY
    })

    console.log(JSON.stringify(subscription))
    // await axios.post('http://localhost:8080/push/sub', subscription)
  }
}
</script>

<template>
  <button @click="subscribePush">알림 구독</button>
</template>

<style scoped>

</style>
