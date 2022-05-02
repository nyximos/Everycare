<template>
<div>
    <v-calendar
        :event-color="getEventColor"
        :events="events"
        :start="start"
        :type="type"
        @click:date="open"
        @click:event="showEvent"
        @click:more="moreEvent"
        @click:time="open"
        dark
        ref="calendar"

        v-model="start"
></v-calendar>
</div>
</template>

<script>
export default {

}
</script>

<style>

</style>