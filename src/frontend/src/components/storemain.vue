<template>
<div>
<div class="container">
  <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1">
      </div>
      <div class="col-4 text-center">
        <p class="display-5 fw-bold">Carestore</p>
      </div>
      <div class="col-4 d-grid gap-2 d-md-flex justify-content-md-end">
      <button type="button" class="btn btn-light position-relative" onclick="location.href = '/cart'">
					🛒
					<!-- <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
						1
					</span> -->
				</button>
				<button class="btn btn-warning" type="button" onclick="location.href = '/login'">입점하기</button>
      </div>
    </div>
  </header>
  
<nav class="nav d-flex justify-content-between">
      <a class="p-2 text-black" href="#">패션/잡화</a>
      <a class="p-2 text-black" href="#">화장품/미용</a>
      <a class="p-2 text-black" href="#">출산/육아</a>
      <a class="p-2 text-black" href="#">생활/건강</a>
      <a class="p-2 text-black" href="#">스포츠/레저</a>
    </nav>
</div>
<!--carousel-->
<div class="container">
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="../images/care.jpg" class="d-block w-100" height="400px">
    </div>
    <div class="carousel-item">
      <img src="../images/sitter.jpg" class="d-block w-100" height="400px">
    </div>
    <div class="carousel-item">
      <img src="../images/year.jpg" class="d-block w-100" height="400px">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<!--ranking-->

<div class="row">
	<p class="fs-2 mt-5 fw-bold">지금 제일 잘 나가는 상품</p>
      <div class="ranking col">
	<img src="../images/year.jpg" class="rounded-circle mx-auto d-block" width="140" height="140">
        <div class="text-center">
		<h2 class="mt-2">체온계</h2>
<button class="btn btn-secondary" href="#">View details &raquo;</button>
		</div>
      </div>

      <div class="ranking col">
        <img src="../images/year.jpg" class="rounded-circle mx-auto d-block" width="140" height="140">
        <div class="text-center">
		<h2 class="mt-2">체온계</h2>
<button class="btn btn-secondary" href="#">View details &raquo;</button>
		</div>
      </div>

     <div class="ranking col">
        <img src="../images/sitter.jpg" class="rounded-circle mx-auto d-block" width="140" height="140">
        <div class="text-center">
		<h2 class="mt-2">체온계</h2>
<button class="btn btn-secondary" href="#">View details &raquo;</button>
		</div>
    </div>
</div>

<!--body-->
		<p class="display-6 text-center mt-5">💪 든든 코로나 방역 키트 💪</p>
		<div class="row mt-5">
				<div class="col" style="width: 18rem;">
					<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
					<div class="card-body">
						<h5 class="card-title">신묘한 응원떡 2종 택1(15개입)</h5>
						<p class="card-text text-muted">신묘한 힘 마약떡 (8개입), 기묘한 힘 인생떡 (8개입)</p>
						<p class="card-cost">10900원</p>
				</div>
				</div>
					<div class="col" style="width: 18rem;">
						<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
						<div class="card-body">
							<h5 class="card-title">신묘한 대박떡 2종(4개입)</h5>
							<p class="card-text text-muted">신묘한 힘 마약떡 (4개입), 기묘한 힘 인생떡 (4개입)</p>
							<p class="card-cost">10900원</p>
						</div>
					</div>
						<div class="col" style="width: 18rem;">
							<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
							<div class="card-body">
								<h5 class="card-title">아침햇살 크림떡(2box)</h5>
								<p class="card-text text-muted">아침햇살을 담아 떡 한입으로 든든한 시작!</p>
								<p class="card-cost">10900원</p>
							</div>
						</div>
					</div>
				
				<p class="display-6 text-center mt-5">💫오늘만 특별 할인💫</p>
				<div class="row mt-5">
				<div class="col" style="width: 18rem;">
					<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
					<div class="card-body">
						<h5 class="card-title">신묘한 응원떡 2종 택1(15개입)</h5>
						<p class="card-text text-muted">신묘한 힘 마약떡 (8개입), 기묘한 힘 인생떡 (8개입)</p>
						<p class="card-cost">10900원</p>
				</div>
				</div>
					<div class="col" style="width: 18rem;">
						<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
						<div class="card-body">
							<h5 class="card-title">신묘한 대박떡 2종(4개입)</h5>
							<p class="card-text text-muted">신묘한 힘 마약떡 (4개입), 기묘한 힘 인생떡 (4개입)</p>
							<p class="card-cost">10900원</p>
						</div>
					</div>
						<div class="col" style="width: 18rem;">
							<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
							<div class="card-body">
								<h5 class="card-title">아침햇살 크림떡(2box)</h5>
								<p class="card-text text-muted">아침햇살을 담아 떡 한입으로 든든한 시작!</p>
								<p class="card-cost">10900원</p>
							</div>
						</div>
					</div>
					<div class="row mt-5">
				<div class="col" style="width: 18rem;">
					<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
					<div class="card-body">
						<h5 class="card-title">신묘한 응원떡 2종 택1(15개입)</h5>
						<p class="card-text text-muted">신묘한 힘 마약떡 (8개입), 기묘한 힘 인생떡 (8개입)</p>
						<p class="card-cost">10900원</p>
				</div>
				</div>
					<div class="col" style="width: 18rem;">
						<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
						<div class="card-body">
							<h5 class="card-title">신묘한 대박떡 2종(4개입)</h5>
							<p class="card-text text-muted">신묘한 힘 마약떡 (4개입), 기묘한 힘 인생떡 (4개입)</p>
							<p class="card-cost">10900원</p>
						</div>
					</div>
						<div class="col" style="width: 18rem;">
							<img src="../images/sitter.jpg" class="card-img-top" alt="..." width="300px" height="200px">
							<div class="card-body">
								<h5 class="card-title">아침햇살 크림떡(2box)</h5>
								<p class="card-text text-muted">아침햇살을 담아 떡 한입으로 든든한 시작!</p>
								<p class="card-cost">10900원</p>
							</div>
						</div>
					</div>

						<p class="display-6 text-center"><span class="fw-bold">생생한</span> 소비자 리뷰</p>
						<p class="fs-6 text-center mt-3"> ▼ 아래 이미지를 클릭하면 자세한 리뷰 내용을 볼 수 있습니다.</p>
						<div class="row justify-content-center">
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
						</div>
						<div class="row justify-content-center mt-3">
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
						</div>
						<div class="row justify-content-center mt-3">
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
						</div>
						<div class="row justify-content-center mt-3">
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
							<div class="col-2"><img src="../images/새싹.jpg" alt="..." class="w-100" width="300px"
									height="100px"></div>
						</div>
 </div>
 <!--footer-->

  <footer class="py-3 my-4">
    <ul class="border-bottom pb-3 mb-3">
    </ul>
    <p class="text-center text-muted">&copy; Everycare</p>
  </footer>

</div>      
</template>

<script>
export default {

}
</script>

<style>
.ranking > img:hover{
    transform:scale(1.2, 1.2);  /* 가로2배 새로 1.5배 로 커짐 */
    transition: transform.5s;  /* 커지는 시간 */
	}
</style>