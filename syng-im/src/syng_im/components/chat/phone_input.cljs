(ns syng-im.components.chat.phone-input
  (:require [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [syng-im.components.react :refer [android?
                                              view
                                              image
                                              text
                                              text-input
                                              touchable-highlight]]
            [syng-im.utils.utils :refer [log toast http-post]]
            [syng-im.utils.logging :as log]
            [syng-im.resources :as res]
            [reagent.core :as r]))

(defn cancel-command-input []
  (dispatch [:set-input-command nil]))

(defn phone-input-view []
  (let [message-atom    (r/atom nil)
        chat-id (subscribe [:get-current-chat-id])]
    (fn []
      (let [message @message-atom]
        [view {:style {:flexDirection "row"}}
         [view {:style {:flexDirection   "column"
                        :backgroundColor "white"}}
          [view {:style {:flexDirection   "column"
                         :margin          10
                         :width           200
                         :backgroundColor "#E5F5F6"
                         :borderRadius    10}}
           [view {:style {:flexDirection "row"}}
            [view {:style {:flexDirection   "column"
                           :margin          10
                           :width           120
                           :backgroundColor "blue"
                           :borderRadius    10}}
             [text {:style {:marginVertical   3
                            :marginHorizontal 10
                            :fontSize         14
                            :fontFamily       "Avenir-Roman"
                            :color           "white"}}
              "!phone"]]
            [touchable-highlight {:style {:marginTop   14
                                          :marginRight 16
                                          :position    "absolute"
                                          :top         3
                                          :right       20}
                                  :onPress (fn []
                                             (cancel-command-input))}
             [image {:source res/att
                     :style  {:width  17
                              :height 14}}]]]
           [text-input {:style                 {:flex       1
                                                :marginLeft 8
                                                :lineHeight 42
                                                :fontSize   14
                                                :fontFamily "Avenir-Roman"
                                                :color      "#9CBFC0"}
                        :underlineColorAndroid "transparent"
                        :autoFocus             true
                        :keyboardType          "phone-pad"
                        :value                 message
                        :onChangeText          (fn [new-text]
                                                 )
                        :onSubmitEditing       (fn [e]
                                                 )}]]]
         [touchable-highlight {:style {:marginTop   14
                                       :marginRight 16
                                       :position    "absolute"
                                       :right       20
                                       :bottom      20}
                               :onPress (fn []
                                          (cancel-command-input))}
          [image {:source res/att
                  :style  {:width  34
                           :height 28}}]]]))))