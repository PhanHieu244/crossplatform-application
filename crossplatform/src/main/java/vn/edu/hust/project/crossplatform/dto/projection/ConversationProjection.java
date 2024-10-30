package vn.edu.hust.project.crossplatform.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import vn.edu.hust.project.crossplatform.dto.ConversationListItemDto;
import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.MessageMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AccountMapper;
import vn.edu.hust.project.crossplatform.service.IAuthService;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class ConversationProjection {
    private Integer conversationId;
    private String partners;
    private Integer messageId;

    public Integer[] getPartnerIds() {
        if (partners == null || partners.isEmpty()) {
            return new Integer[0];
        }

        return Arrays.stream(partners.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    public ConversationListItemDto toConversationListItem(MessageDto messageDto, IAuthService authService) {
        Integer[] partnerIds = getPartnerIds();
        if (partnerIds.length == 0) {
            log.error("partners is null or empty in conversation [id] {}", conversationId);
            throw new ApplicationException("partners is null or empty in conversation [id] " + conversationId);
        }
        // TODO GET ALL IF HAS GROUP CHAT
        var partner = AccountMapper.INSTANCE.accountToUserDto(authService.getAccountById(partnerIds[0]));
        return ConversationListItemDto.builder()
                .id(conversationId)
                .partner(partner)
                .lastMessage(MessageMapper.INSTANCE.toLastMessage(messageDto))
                .build();
    }
}
