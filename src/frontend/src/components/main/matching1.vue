<template>
  <div>
      <div class="title">
          <br>
          <h3>시터찾기</h3>
          <button>등록</button>
      </div>
      <div class="list">
          <ul>
              <li>
                  시터1
              </li>
          </ul>
      </div>
      <div class="text-center">
    <v-pagination
      v-model="page"
      :length="4"
      circle
    ></v-pagination>
  </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            page:1,
        }
    },
}
</script>

<style>

</style>