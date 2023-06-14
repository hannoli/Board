    package com.example.jwtsecurity.domain.service;

    import com.example.jwtsecurity.domain.entity.RefreshToken;
    import com.example.jwtsecurity.domain.repository.RefreshTokenRepository;
    import com.example.jwtsecurity.domain.entity.Token;
    import com.example.jwtsecurity.global.security.jwt.JwtTokenProvider;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class JwtService {
        private final JwtTokenProvider jwtTokenProvider;
        private final RefreshTokenRepository refreshTokenRepository;
        @Transactional
        public void login(Token tokenDto){

            RefreshToken refreshToken = RefreshToken.builder().
                    keyName(tokenDto.getKey())
                    .refreshToken(tokenDto.getRefreshToken())
                    .build();
            String loginUserEmail = refreshToken.getKeyName();
            if(refreshTokenRepository.existsByKeyName(loginUserEmail)){
                log.info("기존의 존재하는 refresh 토큰 삭제");
                refreshTokenRepository.deleteByKeyName(loginUserEmail);
            }
            refreshTokenRepository.save(refreshToken);

        }
        public Optional<RefreshToken> getRefreshToken(String refreshToken){

            System.out.println("리프레시발 " + refreshTokenRepository.findByRefreshToken(refreshToken));
            return refreshTokenRepository.findByRefreshToken(refreshToken);
        }

 /*       public Map<String, String> validateRefreshToken(String refreshToken){
            RefreshToken refreshToken1 = getRefreshToken(refreshToken).get();
            System.out.println("refreshToken1 = " + refreshToken1);
            String createdAccessToken = jwtTokenProvider.validateRefreshToken(refreshToken1);

            return createRefreshJson(createdAccessToken);
        }

*/
 public Map<String, String> validateRefreshToken(String refreshToken) {
     Optional<RefreshToken> optionalRefreshToken = getRefreshToken(refreshToken);
     System.out.println("옵셔널 = " + optionalRefreshToken);
     if (optionalRefreshToken.isPresent()) {
         RefreshToken refreshToken1 = optionalRefreshToken.get();
         System.out.println("refreshToken1 = " + refreshToken1);
         String createdAccessToken = jwtTokenProvider.validateRefreshToken(refreshToken1);
         return createRefreshJson(createdAccessToken);
     } else {
         Map<String, String> map = new HashMap<>();
         map.put("errortype", "Forbidden");
         map.put("status", "402");
         map.put("message", "Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.");
         return map;
     }
 }
        public Map<String, String> createRefreshJson(String createdAccessToken){

            Map<String, String> map = new HashMap<>();
            if(createdAccessToken == null){

                map.put("errortype", "Forbidden");
                map.put("status", "402");
                map.put("message", "Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.");


                return map;
            }
            //기존에 존재하는 accessToken 제거


            map.put("status", "200");
            map.put("message", "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다.");
            map.put("accessToken", createdAccessToken);

            return map;


        }
    }
