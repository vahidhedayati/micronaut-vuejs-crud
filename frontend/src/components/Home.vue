<template>
    <div class="hello">
        <h5>{{ msg }}</h5>
        <h6>This is fab hotel using Micronaut.</h6>
    </div>
</template>

<script>
    export default {
        name: 'Home',
        props: {
            msg: String
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>
