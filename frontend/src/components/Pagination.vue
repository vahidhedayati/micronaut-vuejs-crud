<template>
  <div class="btn-group">
    <button
      type="button"
      class="btn btn-default mb-2"
      @click="onClickFirstPage"
      :disabled="isInFirstPage">
      First
    </button>
    <button
      type="button"
      class="btn btn-default mb-2"
      @click="onClickPreviousPage"
      :disabled="isInFirstPage">
      Previous
    </button>
    <template v-for="page in pages">
      <button
        class="btn btn-default mb-2"
        type="button"
        :key="page.name"
        @click="onClickPage(page.name)"
        :disabled="page.isDisabled"
        :class="{ active: isPageActive(page.name) }">
        {{ page.name }}
      </button>
    </template>
    <button
      type="button"
      class="btn btn-default mb-2"
      @click="onClickNextPage"
      :disabled="isInLastPage">
      Next
    </button>
    <button
      type="button"
      class="btn btn-default mb-2"
      @click="onClickLastPage"
      :disabled="isInLastPage">
      Last
    </button>
  </div>
</template>

<script>

  export default {
    name: 'Pagination',
    props: {
      maxVisibleButtons: {
        type: Number,
        required: false,
        default: 3
      },
      totalPages: {
        type: Number,
        required: true
      },
      total: {
        type: Number,
        required: true
      },
      currentPage: {
        type: Number,
        required: true
      }
    },
    computed: {
      startPage() {
        if (this.currentPage == 1) {
          return 1;
        }

        if (this.currentPage == this.totalPages) {
          return this.totalPages - this.maxVisibleButtons;
        }

        return this.currentPage - 1;
      },
      pages() {
        const range = [];

        for (let i = this.startPage;
             i <= Math.min(this.startPage + this.maxVisibleButtons - 1, this.totalPages);
             i+= 1 ) {
          console.log(' name '+i)
          if (i>0) {
            range.push({
              name: i,
              isDisabled: i == this.currentPage
            });
          }

        }

        return range;
      },
      isInFirstPage() {
        return this.currentPage == 1;
      },
      isInLastPage() {
        console.log(' thi'+this.currentPage+" "+this.totalPages)
        return this.currentPage == this.totalPages;
      },
    },
    methods: {
      onClickFirstPage() {
        this.$emit('pagechanged', 1);
      },
      onClickPreviousPage() {
        this.$emit('pagechanged', this.currentPage - 1);
      },
      onClickPage(page) {
        this.$emit('pagechanged', page);
      },
      onClickNextPage() {
        this.$emit('pagechanged', this.currentPage + 1);
      },
      onClickLastPage() {
        this.$emit('pagechanged', this.totalPages);
      },
      isPageActive(page) {
        return this.currentPage == page;
      }
    }
  };
</script>
<style>
  .pagination {
    list-style-type: none;
  }

  .pagination-item {
    display: inline-block;
  }

  .active {
    background-color: #4AAE9B;
    color: #ffffff;
  }
</style>
