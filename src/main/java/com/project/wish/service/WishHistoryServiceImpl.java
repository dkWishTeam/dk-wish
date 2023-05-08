package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishHistoryCreateDto;
import com.project.wish.dto.WishHistoryRateDto;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.dto.WishHistoryUpdateRequestDto;
import com.project.wish.repository.WishHistoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishHistoryServiceImpl implements WishHistoryService {

    private final WishHistoryRepository wishHistoryRepository;

    @Override
    public List<WishHistoryResponseDto> findWishHistoryListByWishId(Long wishId) {
        List<WishHistory> wishHistoryListByWishId = wishHistoryRepository.findWishHistoryListByWishId(wishId);
        return wishHistoryListByWishId.stream().map(this::wishHistoryToWishHistoryResponseDto).collect(
            Collectors.toList());
    }

    @Override
    public WishHistoryResponseDto findWishHistoryInfoById(Long id) {
        WishHistory wishHistoryById = wishHistoryRepository.findWishHistoryInfoById(id);
        return wishHistoryToWishHistoryResponseDto(wishHistoryById);
    }

    @Override
    public WishHistoryRateDto findRateByWishId(Long wishId) {
        WishHistoryRateDto wishHistoryRateDto = wishHistoryRepository.findRateByWishId(wishId);
        wishHistoryRateDto.setWishId(wishId);
        Long percent = wishHistoryRateDto.getPercent();
        if (percent == 0) {
            wishHistoryRateDto.setCheerUpPhrase(
                "시작이 중요한 거 아시죠? 지금은 아무것도 시작하지 않았지만, 내일이라도 시작하면 되잖아요. <br> 목표를 이루기 위한 첫 걸음을 내딛어보아요!");
        } else if (0 < percent && percent <= 25) {
            wishHistoryRateDto.setCheerUpPhrase(
                "아직 많은 일이 남아있겠죠. 하지만 지금까지 당신이 한 일도 무시할 수 없어요. 조금씩 나아가다보면, <br> 당신의 노력이 결실을 맺게 될 거에요!");
        } else if (25 < percent && percent <= 50) {
            wishHistoryRateDto.setCheerUpPhrase(
                "이제 절반 가까이 왔어요! 이전에는 목표를 이루기 위해 노력을 시작했고, 지금은 그 노력이 결실을 맺고 있어요. <br> 계속해서 당신의 꿈에 가까워지도록 노력해봐요");
        } else if (50 < percent && percent <= 75) {
            wishHistoryRateDto.setCheerUpPhrase(
                "당신은 지금까지 50% 이상을 이루었어요. 이전보다도 더 많은 것을 해냈으니, 더 이상 자신을 감추지 마세요. <br> 목표에 가까이 다가가고 있답니다!");
        } else if (75 < percent && percent < 100){
            wishHistoryRateDto.setCheerUpPhrase(
                "이제는 많이 어려운 시기를 견뎌내고 있는 중이에요. 하지만 목표에 도달하기까지 가장 힘들고 중요한 순간이죠. <br> 조금만 더 힘내세요, 당신은 이루어낼 수 있어요!");
        } else {
            wishHistoryRateDto.setCheerUpPhrase(
                "축하해요! 목표를 이루었어요. 하지만 이제 시작이에요. 이제부터 새로운 목표에 도전할 수 있게 됐답니다. <br> 그리고 또 성공할 거에요!");
        }

        return wishHistoryRateDto;
    }

    @Override
    public void createWishHistory(WishHistoryCreateDto wishHistoryCreateDto) {
        WishHistory wishHistory = wishHistoryCreateDtoToWishHistory(wishHistoryCreateDto);
        wishHistoryRepository.createWishHistory(wishHistory);
    }

    @Override
    public void updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto) {
        WishHistory wishHistory = wishHistoryRepository.findWishHistoryInfoById(
            wishHistoryUpdateRequestDto.getId());
        wishHistory.setModifyDatetime(LocalDateTime.now());
        wishHistory.setAmount(wishHistoryUpdateRequestDto.getAmount());
        wishHistory.setHistoryDatetime(wishHistory.getHistoryDatetime());
        wishHistoryRepository.updateWishHistory(wishHistory);
    }

    @Override
    public boolean deleteWishHistory(Long id) {
        return wishHistoryRepository.deleteWishHistory(id);
    }
}
