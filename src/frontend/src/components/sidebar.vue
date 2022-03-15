<template>
<!--sidebar-->
	<div class="d-flex flex-column flex-shrink-0 p-3 bg-light" id="sidebar">

		<div class="dropdown">
			<a href="#" class="d-flex align-items-center text-black text-decoration-none dropdown-toggle"
				id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
				<img src="https://github.com/mdo.png" alt="" width="100" height="100" class="rounded-circle justify-content-center">
			</a>
            <strong class="text-center fs-4">${name}님</strong>
			<ul class="dropdown-menu dropdown-menu-light text-small shadow" aria-labelledby="dropdownUser1">
				<li><a class="dropdown-item" href="#">마이페이지</a></li>
				<li><a class="dropdown-item" href="#">프로필 수정</a></li>
				<li><a class="dropdown-item" href="#">설정</a></li>
				<li>
					<hr class="dropdown-divider">
				</li>
				<li><a class="dropdown-item" href="#">로그아웃</a></li>
			</ul>
		</div>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
				<a href="/add" class="nav-link active" aria-current="page">
					<svg class="bi me-2" width="8" height="16">
					</svg>
					<i class="fas fa-plus"></i> 월별 주문 수
				</a>
			</li>
			<li>
				<a href="/edit" class="nav-link text-black">
					<svg class="bi me-2" width="8" height="16">
					</svg>
					<i class="fas fa-wrench"></i> 전체 성과
				</a>
			</li>
			<li>
				<a href="/addAdmin" class="nav-link text-black">
					<svg class="bi me-2" width="8" height="16">	
					</svg>
					<i class="fas fa-user-plus"></i> 상품 관리
				</a>
			</li>
			<li>
				<a href="/graph" class="nav-link text-black">
					<svg class="bi me-2" width="8" height="16">
					</svg>
					<i class="fas fa-chart-pie"></i> 주문 정보
				</a>
			</li>
			<li>
				<a href="/edituser" class="nav-link text-black">
					<svg class="bi me-2" width="8" height="16">
						<use xlink:href="#people-circle" />
					</svg>
					<i class="fas fa-users-cog"></i> 정보 수정
				</a>
			</li>
            <li>
				<a href="/edituser" class="nav-link text-black">
					<svg class="bi me-2" width="8" height="16">
						<use xlink:href="#people-circle" />
					</svg>
					<i class="fa-solid fa-volume"></i> 공지사항
				</a>
			</li>
            
			
		</ul>
		<hr>
		
				<button href="/edituser" class="nav-link text-black btn btn-outline-danger">
					스토어 삭제
				</button> 
	</div>    
</template>

<script>
export default {

}
</script>

<style>

</style>